package com.gymix.player.state

import com.gymix.player.PlayerService

class MediaPreparedState constructor(player: PlayerService) : MediaPlayerState(player) {

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
        //nothing
    }

    override fun onSkip5SecBackward() {
        //nothing
    }
}