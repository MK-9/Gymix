package com.gymix.player

import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlayerService @Inject constructor() : Service(),
    MediaPlayer.OnPreparedListener,
    MediaPlayer.OnCompletionListener,
    MediaPlayer.OnErrorListener,
    MediaPlayer.OnSeekCompleteListener,
    MediaPlayer.OnInfoListener,
    MediaPlayer.OnBufferingUpdateListener,
    AudioManager.OnAudioFocusChangeListener {

    private var mediaPlayer: MediaPlayer? = null
    private val mediaFile: String? = null

    private fun initMediaPlayer() {
        mediaPlayer = MediaPlayer().apply {
            setOnPreparedListener(this@PlayerService)
            setOnCompletionListener(this@PlayerService)
            setOnErrorListener(this@PlayerService)
            setOnSeekCompleteListener(this@PlayerService)
            setOnInfoListener(this@PlayerService)
            setOnBufferingUpdateListener(this@PlayerService)
        }
        //Reset so that the MediaPlayer is not pointing to another data source
        mediaPlayer?.reset()

        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            mediaPlayer?.setDataSource(mediaFile)
        } catch (e: Exception) {
            e.printStackTrace()
            stopSelf()
        }
        mediaPlayer?.prepareAsync()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        //Invoked when the media source is ready for playback.

    }

    override fun onCompletion(p0: MediaPlayer?) {
        //Invoked when playback of a media source has completed.

    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        //Invoked when there has been an error during an asynchronous operation.
        return false
    }

    override fun onSeekComplete(p0: MediaPlayer?) {
        //Invoked indicating the completion of a seek operation.
    }

    override fun onInfo(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        //Invoked when the audio focus of the system is updated.
        return false
    }

    override fun onBufferingUpdate(p0: MediaPlayer?, p1: Int) {
        //Invoked indicating buffering status of
        //a media resource being streamed over the network.
    }

    override fun onAudioFocusChange(p0: Int) {
        //Invoked when the audio focus of the system is updated.
    }

    override fun onBind(p0: Intent?): IBinder {
        return PlayerBinder()
    }

    inner class PlayerBinder : Binder() {
        fun getPlayerService(): PlayerService {
            return this@PlayerService
        }
    }
}