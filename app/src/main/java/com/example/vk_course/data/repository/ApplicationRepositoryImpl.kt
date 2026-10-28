package com.example.vk_course.data.repository

import com.example.vk_course.data.ApplicationMapper
import com.example.vk_course.data.MockData
import com.example.vk_course.data.local.ApplicationDao
import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.domain.model.Application
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(val dao: ApplicationDao) :
    ApplicationRepository {
    override fun getAllApplications(): Flow<List<Application>> =
        dao.getAll().map { entities -> entities.map { app -> ApplicationMapper.toDomain(app) } }

    override suspend fun getVKApplicationByID(id: Int): Application? {
        return dao.getByID(id)?.let { app->
            ApplicationMapper.toDomain(app)
        }
    }

    override suspend fun initializeDatabase() {
        if (dao.getApplicationsCount() == 0) {
            MockData.mockApplications.forEach { application ->
                dao.insert(application)
            }
        }
    }
}