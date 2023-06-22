package com.gymix.training.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.gymix.training.R

class NotificationHandler constructor(val context: Context) {

    private var _notificationManager: NotificationManager? = null
    private val notificationManager: NotificationManager get() = _notificationManager!!

    fun createNotification() {
        _notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(ContextCompat.getColor(context, R.color.colorAccent))
            .setContentTitle("Title")
            .setContentText("Text")
            .setContentIntent(getPendingIntent(OPEN_APP))
            .setDeleteIntent(getPendingIntent(NOTIF_DISMISS))
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .addAction(0, "EDIT_REMINDER", getPendingIntent(EDIT_REMINDER))
            .addAction(0, "POSTPONE_REMINDER", getPendingIntent(POSTPONE_REMINDER))


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            setChannel()
            builder.setChannelId(CHANNEL_ID)
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun cancelNotification(context: Context) {
        notificationManager.cancel(NOTIFICATION_ID)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setChannel() {
        val channel =
            NotificationChannel(
                CHANNEL_ID,
                CHANNEL_TITLE,
                NotificationManager.IMPORTANCE_HIGH
            )
                .apply {
                    setShowBadge(false)
                    description = CHANNEL_DESCRIPTION
                    enableVibration(true)
                    enableLights(false)
                }
        notificationManager.createNotificationChannel(channel)
    }

    private fun getPendingIntent(action: Int): PendingIntent {
        val intent = Intent(context, ReminderService::class.java)
        intent.action = ReminderService.ACTION_CANCEL_ALARM
        val pendingIntent = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(
                context,
                123,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getBroadcast(context, 123, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        return pendingIntent
    }

    companion object {
        //CHANNEL CONST
        const val CHANNEL_ID = "CHANNEL_ID"
        const val CHANNEL_TITLE = "CHANNEL_TITLE"
        const val CHANNEL_DESCRIPTION = "CHANNEL_DESCRIPTION"

        //NOTIFICATION CONST
        const val NOTIFICATION_ID = 1

        //
        const val EDIT_REMINDER = 1
        const val POSTPONE_REMINDER = 2
        const val NOTIF_DISMISS = 3
        const val OPEN_APP = 3
    }
}