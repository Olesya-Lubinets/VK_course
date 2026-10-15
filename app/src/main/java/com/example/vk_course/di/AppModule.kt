package com.example.vk_course.di

import android.content.Context
import androidx.room.Room
import com.example.vk_course.data.AppDatabase
import com.example.vk_course.data.ApplicationDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return  return Room.databaseBuilder( context,
           AppDatabase::class.java,
            "applications_database")
            .build()
    }

    @Provides
    @Singleton
    fun provideApplicationDao(
        database: AppDatabase
    ): ApplicationDao = database.applicationDao()

}