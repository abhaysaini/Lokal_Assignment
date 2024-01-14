package com.example.lokal_assignment.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lokal_assignment.data.repository.Repository

class HomeViewFactory(
    private val application: Application,
    private val repository: Repository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            application,
            repository
        ) as T
    }
}