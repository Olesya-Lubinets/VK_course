package com.example.vk_course.data.repository

import com.example.vk_course.data.MockData
import com.example.vk_course.data.local.ApplicationDao
import com.example.vk_course.domain.model.VKApplication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationRepository @Inject constructor(val dao: ApplicationDao) {
    suspend fun  addApplication(VKApplication: VKApplication)  = dao.insert(VKApplication)
    fun getAllApplications():Flow<List<VKApplication>> = dao.getAll()
    suspend fun getVKApplicationByID(id:Int): VKApplication? = dao.getByID(id)
    suspend fun initializeDatabase() {
        if (dao.getApplicationsCount()==0) {
            MockData.mockApplications.forEach { application ->
                dao.insert(application)
            }
        }
    }

}