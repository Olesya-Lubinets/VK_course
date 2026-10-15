package com.example.vk_course.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vk_course.domain.VKApplication


@Database(
    entities = [VKApplication::class ],
        version = 1
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun applicationDao(): ApplicationDao
    }

