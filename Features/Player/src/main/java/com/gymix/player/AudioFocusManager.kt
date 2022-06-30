package com.gymix.player

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.os.Build

class AudioFocusManager constructor(context: Context) : AudioManager.OnAudioFocusChangeListener {

    var audioFocusGainListener: AudioFocusGainListener? = null
    var audioFocusLossListener: AudioFocusLossListener? = null
    var audioFocusLossTransientListener: AudioFocusLossTransientListener? = null
    var audioFocusLossTransientCanDuckListener: AudioFocusLossTransientCanDuck? = null

    private var audioManager: AudioManager? = null
    private lateinit var audioFocusRequest: AudioFocusRequest

    init {
        audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager?
    }

    fun requestAudioFocus(): Boolean {
        val result: Int =
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                audioFocusRequest =
                    AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                        .setAudioAttributes(
                            AudioAttributes.Builder()
                                //useful when we have incoming calls
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                                .build()
                        )
                        .setOnAudioFocusChangeListener(this)
                        .build()
                audioManager?.requestAudioFocus(audioFocusRequest)
            } else {
                audioManager?.requestAudioFocus(
                    this,
                    AudioManager.STREAM_MUSIC,
                    AudioManager.AUDIOFOCUS_GAIN
                )
            } ?: AudioManager.AUDIOFOCUS_REQUEST_FAILED
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    fun removeAudioFocus(): Boolean {
        val result: Int =
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                audioManager?.abandonAudioFocusRequest(audioFocusRequest)
            } else {
                audioManager?.abandonAudioFocus(this)
            } ?: AudioManager.AUDIOFOCUS_REQUEST_FAILED
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    override fun onAudioFocusChange(focusState: Int) {
        //Invoked when the audio focus of the system is updated.
        when (focusState) {
            AudioManager.AUDIOFOCUS_GAIN -> {
                audioFocusGainListener?.onAudioFocusGain()
            }
            AudioManager.AUDIOFOCUS_LOSS -> {
                audioFocusLossListener?.onAudioFocusLoss()
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                audioFocusLossTransientListener?.onAudioFocusLossTransient()
            }
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                audioFocusLossTransientCanDuckListener?.onAudioFocusLossTransientCanDuck()
            }
        }
    }

    interface AudioFocusGainListener {
        fun onAudioFocusGain()
    }

    interface AudioFocusLossListener {
        fun onAudioFocusLoss()
    }

    interface AudioFocusLossTransientListener {
        fun onAudioFocusLossTransient()
    }

    interface AudioFocusLossTransientCanDuck {
        fun onAudioFocusLossTransientCanDuck()
    }
}