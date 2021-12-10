package com.gymix.training

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymixApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}