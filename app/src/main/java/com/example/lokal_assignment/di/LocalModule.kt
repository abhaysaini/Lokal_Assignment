package com.example.lokal_assignment.di

import com.example.lokal_assignment.data.repository.datasource.LocalData
import com.example.lokal_assignment.data.repository.datasourceimpl.LocalDataImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {
    @Singleton
    @Provides
    fun provideLocalDataSource(): LocalData {
        return LocalDataImpl()
    }
}