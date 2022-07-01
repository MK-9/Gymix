package com.gymix.player.state

import com.gymix.player.PlayerService

class MediaPausedState constructor(player: PlayerService) : MediaPlayerState(player) {

    override fun onPlay() {
        player.changeState(MediaStartedState(player))
    }

    override fun onNextTrack() {
        //nothing
    }

    override fun onPreviousTrack() {
        //nothing
    }

    override fun onSkip5SecForward() {
        player.changeState(MediaPausedState(player))
    }

    override fun onSkip5SecBackward() {
        player.changeState(MediaPausedState(player))
    }
}