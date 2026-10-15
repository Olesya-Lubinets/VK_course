package com.example.vk_course.repository

import com.example.vk_course.data.ApplicationDao
import com.example.vk_course.domain.VKApplication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepository @Inject constructor(val dao: ApplicationDao) {
    suspend fun  addApplication(VKApplication: VKApplication) = dao.insert(VKApplication)
    fun getAllApplications():Flow<List<VKApplication>> = dao.getAll()
}