package com.example.vk_course.data.remote

import kotlinx.coroutines.delay
import retrofit2.http.GET
import retrofit2.http.Path


interface AppsAPIService {

    @GET("catalog")
    suspend fun getAllApps(): List<ApplicationApiListItemDTO>

    @GET("catalog/{id}")
    suspend fun getApplication(@Path("id") id: String): ApplicationApiDTO?

}