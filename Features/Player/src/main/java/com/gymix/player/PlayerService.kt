package com.gymix.player

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioFocusRequest
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
    private var position: Int = 0

    private var audioManager: AudioManager? = null

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

    private fun playMedia() {
        mediaPlayer?.run {
            if (!isPlaying) {
                start()
            }
        }
    }

    private fun pauseMedia() {
        mediaPlayer?.run {
            if (isPlaying) {
                pause()
                position = currentPosition
            }
        }
    }

    private fun stopMedia() {
        mediaPlayer?.run {
            if (isPlaying) {
                stop()
            }
        }
    }

    private fun resumeMedia() {
        mediaPlayer?.run {
            if (!isPlaying) {
                seekTo(currentPosition)
                start()
            }
        }
    }

    override fun onPrepared(player: MediaPlayer?) {
        //Invoked when the media source is ready for playback.
        playMedia()
    }

    override fun onCompletion(player: MediaPlayer?) {
        //Invoked when playback of a media source has completed.
        stopMedia()

        //stop the service
        stopSelf()
    }

    override fun onError(player: MediaPlayer?, errorType: Int, extraCode: Int): Boolean {
        //Invoked when there has been an error during an asynchronous operation.
        return false
    }

    override fun onSeekComplete(player: MediaPlayer?) {
        //Invoked indicating the completion of a seek operation.
    }

    override fun onInfo(player: MediaPlayer?, infoType: Int, extraCode: Int): Boolean {
        //Invoked when the audio focus of the system is updated.
        return false
    }

    override fun onBufferingUpdate(player: MediaPlayer?, percentage: Int) {
        //Invoked indicating buffering status of
        //a media resource being streamed over the network.
    }

    private fun requestAudioFocus(): Boolean {
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager?
        val result = audioManager?.requestAudioFocus(
            this,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN
        )
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    private fun removeAudioFocus(): Boolean {
        val result = audioManager?.abandonAudioFocus(this)
        return result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    override fun onAudioFocusChange(focusState: Int) {
        //Invoked when the audio focus of the system is updated.
        when (focusState) {
            AudioManager.AUDIOFOCUS_GAIN -> {
                mediaPlayer?.run {
                    if (!isPlaying) start()
                } ?: run {
                    if (mediaPlayer == null) initMediaPlayer()
                }
                mediaPlayer?.setVolume(1f, 1f)
            }

            AudioManager.AUDIOFOCUS_LOSS -> {
                mediaPlayer?.run {
                    if (isPlaying) stop()
                    release()
                    mediaPlayer = null
                }
            }

            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                mediaPlayer?.run {
                    if (isPlaying) pause()
                }
            }

            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                mediaPlayer?.run {
                    if (isPlaying) setVolume(1f, 1f)
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder {
        return PlayerBinder()
    }

    inner class PlayerBinder : Binder() {
        fun getPlayerService(): PlayerService {
            return this@PlayerService
        }
    }
}