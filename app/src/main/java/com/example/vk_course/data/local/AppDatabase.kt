package com.example.vk_course.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(Converters::class)
@Database(
    entities = [ApplicationDTO::class ],
        version = 4
    )
    abstract class AppDatabase : RoomDatabase() {
        abstract fun applicationDao(): ApplicationDao
    }

