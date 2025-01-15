package com.example.Firebase.ui.ViewModel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.Firebase.MahasiswaApplication

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(mhsApp().container.mahasiswaRepository)
        }
        initializer {
            InsertMhsViewModel(mhsApp().container.mahasiswaRepository)
        }
        initializer {
            DetailViewModel(
                this.createSavedStateHandle(),
                mhsApp().container.mahasiswaRepository
            )
        }
    }
}

fun CreationExtras.mhsApp(): MahasiswaApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as MahasiswaApplication)