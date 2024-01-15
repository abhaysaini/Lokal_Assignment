package com.example.lokal_assignment.di

import android.content.Context
import com.example.lokal_assignment.data.repository.Repository
import com.example.lokal_assignment.data.repository.RepositoryImpl
import com.example.lokal_assignment.data.repository.datasource.CacheData
import com.example.lokal_assignment.data.repository.datasource.LocalData
import com.example.lokal_assignment.data.repository.datasource.RemoteData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext applicationContext : Context,
        cacheDataSource: CacheData,
        localDataSource: LocalData,
        remoteDataSource: RemoteData
    ) : Repository {
        return RepositoryImpl(applicationContext, cacheDataSource, localDataSource, remoteDataSource)
    }
}