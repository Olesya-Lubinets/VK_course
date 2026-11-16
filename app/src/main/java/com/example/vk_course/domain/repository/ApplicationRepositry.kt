package com.example.vk_course.domain.repository

import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.domain.model.Application
import kotlinx.coroutines.flow.Flow

interface ApplicationRepository {
    fun getAllApplications(): Flow<List<Application>>
    suspend fun getVKApplicationByID(id:String): Application?
    suspend fun addApplicationToDB(application:Application)
    suspend fun deleteApplicationFromDB(application: ApplicationDTO)
}