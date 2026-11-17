package com.example.vk_course.data.repository

import com.example.vk_course.data.remote.AppsAPIService
import com.example.vk_course.data.ApplicationMapper
import com.example.vk_course.data.local.ApplicationDTO
import com.example.vk_course.data.local.ApplicationDao
import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.domain.model.Application
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApplicationRepositoryImpl @Inject constructor(
    private val apiService: AppsAPIService,
    private val dbDao: ApplicationDao
) : ApplicationRepository {

    override fun getAllApplications(): Flow<List<Application>> =
        flow {
            val apps = apiService.getAllApps().map { it -> ApplicationMapper.toDomain(it) }
            emit(apps)
        }


    override suspend fun getVKApplicationByID(id: String): Application? {
        return apiService.getApplication(id)?.let { app ->
            ApplicationMapper.toDomain(app)
        }
    }

    override suspend fun addApplicationToDB(application: Application) {
        dbDao.insert(ApplicationMapper.toDB(application))
    }

    override suspend fun deleteApplicationFromDB(application: Application) {
        dbDao.deleteAppById(application.id)
    }

    override suspend fun getRating(id: String): Float {
        delay(1500)
        return (30..50).random() / 10f
    }

    override suspend fun loadRatingsSequentially(ids:List<String>): List<Pair<String, Float>> {
       return  ids.map { id -> id to getRating(id) }
    }

    override suspend fun loadRatingsInParallel(ids: List<String>) =
        coroutineScope {
            ids.map { id -> async { id to getRating(id) } }.awaitAll()
        }
}

