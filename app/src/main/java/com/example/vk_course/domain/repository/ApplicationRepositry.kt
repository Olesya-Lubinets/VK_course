package com.example.vk_course.domain.repository

import com.example.vk_course.domain.model.Application
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
    fun getAllApplications(): Flow<List<Application>>
    suspend fun getVKApplicationByID(id:Int): Application?
    suspend fun initializeDatabase()
}