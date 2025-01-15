package com.example.Firebase.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenis_kelamin: String,
    val alamat: String,
    val kelas: String,
    val angkatan: String,
    val judul_skripsi: String,
    val dosen_pembimbing: String,
    val dosen_pembimbing_2: String
){
    constructor(): this("","","","","","","","","")
}

data class MahasiswaResponDetail(
    val data: Mahasiswa
)