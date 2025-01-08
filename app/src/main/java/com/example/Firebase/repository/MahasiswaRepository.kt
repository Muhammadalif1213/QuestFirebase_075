package com.example.Firebase.repository

import com.example.Firebase.model.Mahasiswa
import kotlinx.coroutines.flow.Flow


interface MahasiswaRepository{
    suspend fun getMahasiswa(): Flow<List<Mahasiswa>>


}