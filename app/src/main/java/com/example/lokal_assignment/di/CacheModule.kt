package com.example.lokal_assignment.di

import com.example.lokal_assignment.data.repository.datasource.CacheData
import com.example.lokal_assignment.data.repository.datasourceimpl.CacheDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
        @Singleton
        @Provides
        fun provideCacheDataSource(): CacheData {
            return CacheDataImpl()
        }
}