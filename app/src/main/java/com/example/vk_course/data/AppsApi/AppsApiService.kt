package com.example.vk_course.data.AppsApi

import com.example.vk_course.data.local.ApplicationApiDTO
import com.example.vk_course.data.local.ApplicationApiListItemDTO
import com.example.vk_course.data.local.ApplicationDTO
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path


interface AppsAPIService {

    @GET("catalog")
    suspend fun getAllApps(
    ): List<ApplicationApiListItemDTO>

    @GET("{id}")
    suspend fun getApplication(@Path("id") id:String): ApplicationApiDTO?
}