package com.gymix.training.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        val extras = intent?.extras
        val id = extras?.getInt(ReminderService.EXTRA_RECEIVER_ID)
        Log.d("ReminderReceiver", " ---$id--- time: " + System.currentTimeMillis())
    }
}