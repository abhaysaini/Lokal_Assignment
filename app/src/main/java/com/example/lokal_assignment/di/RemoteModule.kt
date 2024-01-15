package com.example.lokal_assignment.di

import com.example.lokal_assignment.data.repository.datasource.RemoteData
import com.example.lokal_assignment.data.repository.datasourceimpl.RemoteDataImpl
import com.example.lokal_assignment.network.APIservice
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: APIservice) : RemoteData {
        return RemoteDataImpl(apiService)
    }
}