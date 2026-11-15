package com.example.vk_course.data.local

import com.example.vk_course.domain.model.Category

data class ApplicationApiDTO(
    val id: String,
    val iconUrl:String,
    val name: String,
    val description: String,
    val category: Category,
    val developer: String,
    val ageRating: Int,
    val screenshotUrlList: List<String>?,
    val size: Float,
)

data class ApplicationApiListItemDTO(
    val id: String,
    val iconUrl:String,
    val name: String,
    val description: String,
    val category: String
)
