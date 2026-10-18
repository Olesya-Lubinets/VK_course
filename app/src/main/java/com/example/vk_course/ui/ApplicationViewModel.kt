package com.example.vk_course.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_course.repository.ApplicationRepository
import com.example.vk_course.DBState
import com.example.vk_course.domain.VKApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ApplicationViewModel @Inject constructor(private val repository: ApplicationRepository) :ViewModel() {


    val listOfApplications: StateFlow<DBState>
        = repository.getAllApplications().map {
            applications ->
        DBState.Success(applications)
        }
            .catch { DBState.Error }
            .stateIn( scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DBState.Loading
            )


    fun addApplication(application:VKApplication) {
        viewModelScope.launch {
            repository.addApplication(application)
        }
    }
}