package com.example.vk_course.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.vk_course.domain.model.AgeRating
import com.example.vk_course.domain.model.Category

@Entity(tableName = "applications")
data class ApplicationDTO(
    @PrimaryKey(autoGenerate = true)
    val idDB: Int = 0,
    val id: String ,
    val iconUrl: String,
    val name: String,
    val description: String,
    val category: Category,
    val developer: String?,
    val ageRating: AgeRating?,
    val screenshotUrlList: List<String>?,
    val size: Float?,
)
