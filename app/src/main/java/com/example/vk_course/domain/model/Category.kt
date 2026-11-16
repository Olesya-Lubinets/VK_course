package com.example.vk_course.domain.model

import androidx.annotation.StringRes
import com.example.vk_course.R

enum class Category {
    PRODUCTIVITY,
    HEALTH_FITNESS,
    PHOTO_VIDEO,
    FOOD_DRINKS,
    EDUCATION,
    LIFESTYLE,
    SHOPPING,
    NEWS,
    MUSIC,
    GAME,
    FINANCE,
    UTILITIES,
    NAVIGATION,
    SOCIAL,
    BUSINESS,
    WEATHER,
    ENTERTAINMENT,
    BOOKS_REFERENCE,
    OTHER
}

@StringRes
fun Category.getCategoryFromResources(): Int {
    return when (this) {
        Category.PRODUCTIVITY -> R.string.category_productivity
        Category.HEALTH_FITNESS -> R.string.category_health_fitness
        Category.PHOTO_VIDEO -> R.string.category_photo_video
        Category.FOOD_DRINKS -> R.string.category_food_drinks
        Category.EDUCATION -> R.string.category_education
        Category.LIFESTYLE -> R.string.category_lifestyle
        Category.SHOPPING -> R.string.category_shopping
        Category.NEWS -> R.string.category_news
        Category.MUSIC -> R.string.category_music
        Category.GAME -> R.string.category_game
        Category.FINANCE -> R.string.category_finance
        Category.UTILITIES -> R.string.category_utilities
        Category.NAVIGATION -> R.string.category_navigation
        Category.SOCIAL -> R.string.category_social
        Category.BUSINESS -> R.string.category_business
        Category.WEATHER -> R.string.category_weather
        Category.ENTERTAINMENT -> R.string.category_entertainment
        Category.BOOKS_REFERENCE -> R.string.category_books_reference
        Category.OTHER -> R.string.category_other
    }
}

fun String.toCategory(): Category {
    return when (this) {
        "Производительность" -> Category.PRODUCTIVITY
        "Здоровье и фитнес" -> Category.HEALTH_FITNESS
        "Фото и видео" -> Category.PHOTO_VIDEO
        "Еда и напитки" -> Category.FOOD_DRINKS
        "Образование" -> Category.EDUCATION
        "Образ жизни" -> Category.LIFESTYLE
        "Шопинг" -> Category.SHOPPING
        "Новости" -> Category.NEWS
        "Музыка" -> Category.MUSIC
        "Игры" -> Category.GAME
        "Финансы" -> Category.FINANCE
        "Утилиты" -> Category.UTILITIES
        "Навигация" -> Category.NAVIGATION
        "Общение" -> Category.SOCIAL
        "Бизнес" -> Category.BUSINESS
        "Погода" -> Category.WEATHER
        "Развлечения" -> Category.ENTERTAINMENT
        "Книги и справочники" -> Category.BOOKS_REFERENCE
        else -> Category.OTHER
    }
}