package com.example.vk_course.data.remote

import retrofit2.http.GET
import retrofit2.http.Path


interface AppsAPIService {

    @GET("catalog")
    suspend fun getAllApps(
    ): List<ApplicationApiListItemDTO>

    @GET("{id}")
    suspend fun getApplication(@Path("id") id: String): ApplicationApiDTO?
}