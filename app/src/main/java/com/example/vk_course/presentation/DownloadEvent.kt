package com.example.vk_course.presentation

import com.example.vk_course.domain.model.VKApplication

sealed interface DownloadEvent {
    data class Error(val message:String): DownloadEvent
    data class Success(val message:String): DownloadEvent
}