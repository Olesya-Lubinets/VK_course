package com.example.vk_course.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "applications")
data class VKApplication(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val icon:String,
    val title: String,
    val description: String,
    val category:Category
)

enum class Category {
    LIFESTYLE, SOCIAL, PRODUCTIVITY, MEDIA, GAME, FINANCE
}