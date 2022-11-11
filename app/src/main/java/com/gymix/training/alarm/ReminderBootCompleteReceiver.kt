package com.gymix.training.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ReminderBootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.run {
            if (action == Intent.ACTION_BOOT_COMPLETED) {
                Log.d("ReminderReceiver", "Boot_Completed")

                Toast.makeText(context, "ReminderReceiver", Toast.LENGTH_SHORT).show()
            }
        }
    }
}