package com.gymix.player

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BecomeNoisyReceiver : BroadcastReceiver() {

    var callback: OnAudioBecomeNoisyListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        //pause audio on ACTION_AUDIO_BECOMING_NOISY
        callback?.onAudioBecomeNoisy()
    }

    fun setAudioBecomeNoisyListener(callback: OnAudioBecomeNoisyListener?) {
        this.callback = callback
    }

    interface OnAudioBecomeNoisyListener {
        fun onAudioBecomeNoisy()
    }
}