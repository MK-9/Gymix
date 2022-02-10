package com.gymix.training

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class MyPlayerService : Service() {

    private val CHANNEL_ID = "channelId"
    lateinit var mediaPlayer: MediaPlayer
    lateinit var notificationManager: NotificationManager

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "onCreate service", Toast.LENGTH_SHORT).show()
        mediaPlayer = MediaPlayer.create(this, R.raw.hotel_california)
        mediaPlayer.isLooping = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "onStart service", Toast.LENGTH_SHORT).show()


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "aaa", NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Music Channel"
            notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }


        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("music")
                .setContentText("music is running")
                .setSmallIcon(R.drawable.ic_profile)

        val notification = notificationBuilder.build()

        startForeground(1, notification)

        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "onDestroy service", Toast.LENGTH_SHORT).show()
        mediaPlayer.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}