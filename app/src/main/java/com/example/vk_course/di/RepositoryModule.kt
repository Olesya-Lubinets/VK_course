package com.example.vk_course.di

import com.example.vk_course.domain.repository.ApplicationRepository
import com.example.vk_course.data.repository.ApplicationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideApplicationRepository(
        implementation: ApplicationRepositoryImpl
    ): ApplicationRepository
}