package com.example.Firebase.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Firebase.model.Mahasiswa
import com.example.Firebase.repository.MahasiswaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


sealed class DetailUiState{
    data class Success(val mahasiswa: Flow<Mahasiswa>): DetailUiState()
    data object Error: DetailUiState()
    object Loading: DetailUiState()
}


class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val mhs: MahasiswaRepository
): ViewModel() {
    private val nim: String = checkNotNull(savedStateHandle["nim"])
    var detailMhsUiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    init {
        getMhsById()
    }

    fun getMhsById() {
        viewModelScope.launch {
            detailMhsUiState = DetailUiState.Loading
            detailMhsUiState = try {
                DetailUiState.Success(mhs.getMahasiswaByNim(nim))
            } catch (e: Exception) {
                DetailUiState.Error
            }
        }
    }

}