package com.example.vk_course.data.remote

data class ApplicationApiDTO(
    val id: String,
    val iconUrl: String,
    val name: String,
    val description: String,
    val category: String,
    val developer: String,
    val ageRating: Int,
    val screenshotUrlList: List<String>?,
    val size: Float,
)

