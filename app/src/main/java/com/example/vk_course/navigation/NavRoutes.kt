package com.example.vk_course.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed interface NavRoutes {
    @Serializable
    data object AppList :NavRoutes
    @Serializable
    data class AppDetails(val id: Int): NavRoutes

}