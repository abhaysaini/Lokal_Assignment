package com.example.lokal_assignment.di

import android.app.Application
import com.example.lokal_assignment.data.repository.Repository
import com.example.lokal_assignment.viewModels.HomeViewFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideHomeViewModelFactory(app : Application, repository: Repository) : HomeViewFactory {
        return HomeViewFactory(app, repository)
    }

}