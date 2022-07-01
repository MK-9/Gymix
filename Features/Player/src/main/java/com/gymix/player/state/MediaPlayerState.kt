package com.gymix.player.state

import com.gymix.player.PlayerService

abstract class MediaPlayerState constructor(val player: PlayerService) {
    abstract fun onPlay()
    abstract fun onNextTrack()
    abstract fun onPreviousTrack()
    abstract fun onSkip5SecForward()
    abstract fun onSkip5SecBackward()
}