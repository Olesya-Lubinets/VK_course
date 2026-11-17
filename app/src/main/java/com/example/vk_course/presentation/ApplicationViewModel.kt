package com.example.vk_course.presentation

import android.database.sqlite.SQLiteException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.domain.model.Application
import com.example.vk_course.presentation.appDetailsScreen.InstallState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplicationViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {


    val listOfApplications: StateFlow<AppListState> =
        repository.getAllApplications().map<List<Application>, AppListState> { applications ->
            AppListState.Success(applications)
        }
            .catch { emit(AppListState.Error) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AppListState.Loading
            )


    private val _appByID = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val appByID = _appByID.asStateFlow()


    fun getById(id: String) {
        viewModelScope.launch {
            try {
                val app = repository.getVKApplicationByID(id)
                if (app != null) {
                    _appByID.value = AppDetailsState.Content(app, InstallState.NotInstalled)

                } else _appByID.value = AppDetailsState.Error
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _appByID.value = AppDetailsState.Error
            }
        }
    }

    private val _downloadEvent = Channel<DownloadEvent>(Channel.BUFFERED)
    val downloadEvent = _downloadEvent.receiveAsFlow()


    private suspend fun emulateInstall(application: Application) {
        if (_appByID.value !is AppDetailsState.Content) return
        try {
            for (progress in 0..100 step 5) {
                delay(150)
                updateInstallState(InstallState.Installing(progress))
            }
            repository.addApplicationToDB(application = application)
            updateInstallState(InstallState.Installed)
            _downloadEvent.send(DownloadEvent.Success("Успешная загрузка"))
        } catch (e: SQLiteException) {
            updateInstallState(InstallState.NotInstalled)
            _downloadEvent.send(DownloadEvent.Error("Неуспешная загрузка"))
        } catch (e: CancellationException) {
            updateInstallState(InstallState.NotInstalled)
            throw e
        }
    }

    private fun updateInstallState(installState: InstallState) {
        _appByID.update { appDetailsState ->
            if (appDetailsState is AppDetailsState.Content) {
                appDetailsState.copy(installState = installState)
            } else {
                appDetailsState
            }
        }
    }

    private var installJob: Job? = null

    fun onInstallClick(app: Application) {
        if (installJob?.isActive == true) {
            installJob?.cancel()
        } else {
            installJob = viewModelScope.launch {
                emulateInstall(app)
            }
        }
    }
}


