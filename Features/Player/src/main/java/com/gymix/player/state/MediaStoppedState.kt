package com.gymix.player.state

import com.gymix.player.PlayerService

class MediaStoppedState constructor(player: PlayerService) : MediaPlayerState(player) {

    override fun onPlay() {

    }

    override fun onNextTrack() {

    }

    override fun onPreviousTrack() {

    }

    override fun onSkip5SecForward() {

    }

    override fun onSkip5SecBackward() {

    }
}