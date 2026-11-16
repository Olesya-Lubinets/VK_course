package com.example.vk_course.domain.model

data class Application(
    val id: String,
    val iconUrl: String,
    val name: String,
    val description: String,
    val category: Category,
    val developer: String?,
    val ageRating: AgeRating?,
    val screenshotUrlList: List<String>?,
    val size: Float?,
)
