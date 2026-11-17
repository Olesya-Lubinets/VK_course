package com.example.vk_course.presentation

import com.example.vk_course.domain.model.Application
import com.example.vk_course.presentation.appDetailsScreen.InstallState

sealed class AppDetailsState {
    data object Error : AppDetailsState()
    data object Loading : AppDetailsState()
    data class Content(
        val application: Application,
        val installState: InstallState
    ) : AppDetailsState()
}