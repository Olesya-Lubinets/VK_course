package com.example.vk_course.domain.model

import androidx.annotation.StringRes
import com.example.vk_course.R


data class Application(
    val id: Int,
    val iconUrl:String,
    val name: String,
    val description: String,
    val category: Category,
    val developer: String,
    val ageRating: AgeRating,
    val screenshotUrlList: List<String>?,
    val size: Float,
    )

enum class Category {
    LIFESTYLE, SOCIAL, PRODUCTIVITY
    , MEDIA, GAME, FINANCE
}

@StringRes
fun Category.getCategoryFromResources():Int {
    return when (this) {
        Category.LIFESTYLE -> R.string.category_lifestyle
        Category.SOCIAL ->  R.string.category_social
        Category.PRODUCTIVITY -> R.string.category_productivity
        Category.MEDIA -> R.string.category_media
        Category.GAME -> R.string.category_game
        Category.FINANCE -> R.string.category_finance
    }
}