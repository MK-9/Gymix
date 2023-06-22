package com.gymix.training.alarm

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Binder
import android.os.Build
import android.os.IBinder

class ReminderService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.action?.let {
            handleIntent(intent)
        }
        return START_STICKY
    }

    private fun handleIntent(intent: Intent) {
        intent.extras?.let { extras ->
            when (intent.action) {
                ACTION_SET_ALARM -> {
                    val id = extras.getInt(EXTRA_ALARM_ID)
                    val hour = extras.getInt(EXTRA_ALARM_HOUR)
                    val min = extras.getInt(EXTRA_ALARM_MIN)
                    setAlarm(baseContext, id, hour, min)
                }

                ACTION_SET_REPEATING_ALARM -> {
                }

                ACTION_CANCEL_ALARM -> {
                    val id = extras.getInt(EXTRA_ALARM_ID)
                    cancelAlarm(baseContext, id)
                }
            }
        }

        stopSelf()
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun setAlarm(context: Context, id: Int, hour: Int, min: Int) {
        val intent = Intent(context, ReminderReceiver::class.java)
        intent.putExtra(EXTRA_RECEIVER_ID, id)
        val pendingIntent = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        scheduleAlarm(context, pendingIntent)
    }

    private fun scheduleAlarm(context: Context, alarmIntent: PendingIntent) {
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, 1 * 1000, 60 * 1000, alarmIntent)
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, 10 * 1000, alarmIntent)
        }

        // Enable {@ReminderBootCompleteReceiver} to automatically restart the alarm when the
        // device is rebooted.
        val receiver = ComponentName(context, ReminderBootCompleteReceiver::class.java)
        val pm = context.packageManager
        pm.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun cancelAlarm(context: Context, id: Int) {
        val alarmManager: AlarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java)
        val pendingIntent = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )
        } else {
            PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        alarmManager.cancel(pendingIntent)
//        pendingIntent.cancel()

        //disable alarm
        val receiver = ComponentName(context, ReminderBootCompleteReceiver::class.java)
        val pm = context.packageManager
        pm.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    override fun onBind(p0: Intent?): IBinder {
        return MyBinder()
    }

    inner class MyBinder : Binder() {
        val service: ReminderService
            get() = this@ReminderService
    }

    companion object {
        //extras
        const val EXTRA_ALARM_ID = "extra_alarm_id"
        const val EXTRA_ALARM_HOUR = "extra_alarm_hour"
        const val EXTRA_ALARM_MIN = "extra_alarm_min"
        const val EXTRA_RECEIVER_ID = "extra_receiver_id"

        //actions
        const val ACTION_SET_ALARM = "actionSetAlarm"
        const val ACTION_SET_REPEATING_ALARM = "actionSetRepeatingAlarm"
        const val ACTION_CANCEL_ALARM = "actionCancelAlarm"

        @JvmStatic
        fun execute(context: Context) {
            val intent = Intent(context, ReminderService::class.java)
            context.startService(intent)
        }

        @JvmStatic
        fun execute(context: Context, action: String, id: Int) {
            val intent = Intent(context, ReminderService::class.java)
            intent.action = action
            intent.putExtra(EXTRA_ALARM_ID, id)
            context.startService(intent)
        }
    }

}