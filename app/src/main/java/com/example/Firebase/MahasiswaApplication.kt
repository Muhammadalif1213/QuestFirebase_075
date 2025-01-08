package com.example.Firebase

import android.app.Application
import com.example.Firebase.DI.AppContainer
import com.example.Firebase.DI.MahasiswaContainer

class MahasiswaApplication: Application(){
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = MahasiswaContainer()
    }
}