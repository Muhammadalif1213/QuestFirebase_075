package com.example.Firebase.model

data class Mahasiswa (
    val nim: String,
    val nama: String,
    val jenis_kelamin: String,
    val alamat: String,
    val kelas: String,
    val angkatan: String
){
    constructor(): this("20220140075","Alifian","Laki-laki","Karawang","B","2022")
}