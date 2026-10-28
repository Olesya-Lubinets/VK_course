package com.example.vk_course.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.domain.model.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(private val repository: ApplicationRepository) :
    ViewModel() {

    init {
        viewModelScope.launch {
            repository.initializeDatabase()
        }
    }

    val listOfApplications: StateFlow<AppListState> =
        repository.getAllApplications().map<List<Application>,AppListState> { applications ->
            AppListState.Success(applications)
        }
            .catch  { emit(AppListState.Error) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = AppListState.Loading
            )


    private val _appByID = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val appByID = _appByID.asStateFlow()


    fun getById(id: Int) {
        viewModelScope.launch {
            try {
                val app = repository.getVKApplicationByID(id)
                if (app != null) {
                    _appByID.value = AppDetailsState.Success(app)

                }
                else  _appByID.value =  AppDetailsState.Error
            } catch (e: Exception) {
                _appByID.value = AppDetailsState.Error
            }
        }
    }

    private val _downloadEvent = Channel<DownloadEvent>(Channel.BUFFERED)
    val downloadEvent = _downloadEvent.receiveAsFlow()

    fun downloadApp(id:Int) {
        viewModelScope.launch {
            _downloadEvent.send(DownloadEvent.Success("Успешная загрузка id:${id}"))
            Log.d("ApplicationViewModel","event send")
        }
    }
}


