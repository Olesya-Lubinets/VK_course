package com.example.vk_course.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.vk_course.domain.model.VKApplication

@TypeConverters(Converters::class)
@Database(
    entities = [VKApplication::class ],
        version = 2
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun applicationDao(): ApplicationDao
    }

