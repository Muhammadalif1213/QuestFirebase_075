package com.example.Firebase.ui.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.Firebase.model.Mahasiswa
import com.example.Firebase.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class InsertMhsViewModel(
    private val mhs: MahasiswaRepository
): ViewModel() {

    var uiEvent: InsertMhsUiState by mutableStateOf(InsertMhsUiState())
        private set
    var uiState: FormState by mutableStateOf(FormState.Idle)
        private set

    fun updateState(mahasiswaEvent: MahasiswaEvent){
        uiEvent = uiEvent.copy(
            insertUiEvent = mahasiswaEvent,
        )
    }

}

sealed interface FormState{
    object Idle: FormState
    object Loading: FormState
    data class Success(val message: String): FormState
    data class Error(val message: String): FormState
}

data class InsertMhsUiState(
    val insertUiEvent: MahasiswaEvent = MahasiswaEvent(),
    val isEntryValid: FormErrorState = FormErrorState()
)

data class FormErrorState(
    val nim: String? = null,
    val nama: String? = null,
    val jenisKelamin: String? = null,
    val alamat: String? = null,
    val kelas: String? = null,
){
    fun isValid(): Boolean{
        return nim != null && nama != null && jenisKelamin != null && alamat != null && kelas != null
    }
}

fun MahasiswaEvent.toMhsModel(): Mahasiswa = Mahasiswa(
    nim = nim,
    nama = nama,
    jenis_kelamin = jenisKelamin,
    alamat = alamat,
    kelas = kelas,
    angkatan = angkatan
)

data class MahasiswaEvent(
    val nim: String = "",
    val nama: String = "",
    val jenisKelamin: String = "",
    val alamat: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)