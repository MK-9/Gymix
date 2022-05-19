package com.gymix.training

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymixApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
    }
}