package com.gymix.training

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper

class MyBoundedService : Service() {

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var mProgress: Int = 0
    private val incrementalValue = 100
    val finishProgress = 5000
    private var isPaused = true

    override fun onCreate() {
        super.onCreate()
        handler = Handler()
    }

    fun startService() {
        runnable = Runnable {
            if (mProgress <= finishProgress) {
                mProgress += incrementalValue
                handler.postDelayed(runnable, 100)
            } else {
                handler.removeCallbacks(runnable)
            }
        }

        handler.postDelayed(runnable, 100)
    }

    fun getProgress(): Float {
        return (mProgress.toFloat() / finishProgress.toFloat()) * 100
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopSelf()
    }

    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        fun getService(): MyBoundedService {
            return this@MyBoundedService
        }
    }
}