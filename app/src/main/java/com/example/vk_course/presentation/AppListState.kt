package com.example.vk_course.presentation

import com.example.vk_course.domain.model.Application

sealed class AppListState {
    data object Error: AppListState()
    data object Loading: AppListState()
    data class Success(val applicationList:List<Application>): AppListState()
}


