package com.example.vk_course.presentation

sealed interface DownloadEvent {
    data class Error(val message:String): DownloadEvent
    data class Success(val message:String): DownloadEvent
}