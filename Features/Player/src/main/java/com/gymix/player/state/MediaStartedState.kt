package com.gymix.player.state

import com.gymix.player.PlayerService

class MediaStartedState constructor(player: PlayerService) : MediaPlayerState(player) {

    override fun onPlay() {
        player.changeState(MediaPausedState(player))
    }

    override fun onNextTrack() {
        //nothing
    }

    override fun onPreviousTrack() {
        //nothing
    }

    override fun onSkip5SecForward() {
        player.changeState(MediaStartedState(player))
    }

    override fun onSkip5SecBackward() {
        player.changeState(MediaStartedState(player))
    }
}