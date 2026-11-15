package com.example.vk_course.di

import com.example.vk_course.data.AppsApi.AppsAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APIModule {
    @Provides
    @Singleton
    fun providesApiClient(): OkHttpClient {

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesChatRetrofit( chatOkHttpClient: OkHttpClient): Retrofit {
        val BASE_URL = "http://185.103.109.134/"

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(chatOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesChatAPIService(retrofit: Retrofit): AppsAPIService {
        return retrofit.create(AppsAPIService::class.java)
    }
}