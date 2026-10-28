package com.example.vk_course.presentation

import com.example.vk_course.domain.model.Application

sealed class AppDetailsState {
        data object Error: AppDetailsState()
        data object Loading: AppDetailsState()
        data class Success(val application: Application): AppDetailsState()
}