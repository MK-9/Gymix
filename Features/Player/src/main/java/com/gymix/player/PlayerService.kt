package com.gymix.player

import android.app.Service
import android.content.Intent
import android.content.IntentFilter
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
    AudioFocusManager.OnAudioFocusGainListener,
    AudioFocusManager.OnAudioFocusLossListener,
    AudioFocusManager.OnAudioFocusLossTransientListener,
    AudioFocusManager.OnAudioFocusLossTransientCanDuck,
    BecomeNoisyReceiver.OnAudioBecomeNoisyListener {

    private var mediaPlayer: MediaPlayer? = null
    private var audioFocusManager: AudioFocusManager? = null
    private var becomeNoisyReceiver: BecomeNoisyReceiver? = null

    private var mediaFile: String? = null
    private var position: Int = 0

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            mediaFile = intent?.extras?.getString("Media")
        } catch (e: Exception) {
            stopSelf()
        }

        //init audio focus manager
        initAudioFocusManager()

        //init audio manger
        if (mediaFile?.isNotEmpty() == true)
            initMediaPlayer()

        return START_NOT_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        //ACTION_AUDIO_BECOMING_NOISY -- change in audio outputs -- BroadcastReceiver
        registerBecomingNoisyReceiver()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.run {
            stopMedia()
            release()
        }
        audioFocusManager?.removeAudioFocus()

        //unregister BroadcastReceivers
        unregisterBecomingNoisyReceiver()
    }

    private fun initAudioFocusManager() {
        audioFocusManager = AudioFocusManager(this)
            .apply {
                if (!requestAudioFocus())
                    stopSelf()
            }.apply {
                audioFocusGainListener = this@PlayerService
                audioFocusLossListener = this@PlayerService
                audioFocusLossTransientListener = this@PlayerService
                audioFocusLossTransientCanDuckListener = this@PlayerService
            }
    }

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

    private fun registerBecomingNoisyReceiver() {
        becomeNoisyReceiver = BecomeNoisyReceiver().apply {
            setAudioBecomeNoisyListener(this@PlayerService)
            registerReceiver(this, IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY))
        }
    }

    private fun unregisterBecomingNoisyReceiver() {
        unregisterReceiver(becomeNoisyReceiver)
    }

    fun changeState(state: MediaPlayerState) {
        this.playerState = state
    }

    fun getMediaPlayerState(): MediaPlayerState {
        return playerState
    }

    private fun playMedia() {
        //we should add requestAudioFocus
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

    override fun onAudioFocusGain() {
        mediaPlayer?.run {
            if (!isPlaying) start()
        } ?: run {
            if (mediaPlayer == null) initMediaPlayer()
        }
        mediaPlayer?.setVolume(1f, 1f)
    }

    override fun onAudioFocusLoss() {
        mediaPlayer?.run {
            if (isPlaying) stop()
            release()
            mediaPlayer = null
        }
    }

    override fun onAudioFocusLossTransient() {
        mediaPlayer?.run {
            if (isPlaying)
                pause()
        }
    }

    override fun onAudioFocusLossTransientCanDuck() {
        mediaPlayer?.run {
            if (isPlaying)
                setVolume(1f, 1f)
        }
    }

    override fun onAudioBecomeNoisy() {
        //pause audio on ACTION_AUDIO_BECOMING_NOISY
        mediaPlayer?.run {
            if (isPlaying)
                pauseMedia()
        }
        //pause state
        buildNotification()
    }

    override fun onBind(intent: Intent?): IBinder {
        return MyBinder()
    }

    private fun buildNotification() {
        //todo
    }

    inner class MyBinder : Binder() {
        fun getPlayerService(): PlayerService {
            return this@PlayerService
        }
    }
}