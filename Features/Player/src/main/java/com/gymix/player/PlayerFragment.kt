package com.gymix.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.gymix.player.databinding.PlayerFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var binding: PlayerFragmentBinding

    private var player: ExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlayerFragmentBinding.inflate(inflater)

        initPlayer()

        return binding.root
    }


    private fun initPlayer() {
        player =
            ExoPlayer.Builder(requireContext())
                .build()
                .also { exoPlayer ->
                    binding.playerView.player = exoPlayer
                }.also { exoPlayer ->
                    val mediaItem =
                        MediaItem.fromUri("https://download.samplelib.com/mp4/sample-5s.mp4")
                    exoPlayer.setMediaItem(mediaItem)
                }.also { exoPlayer ->
                    val mediaItem =
                        MediaItem.fromUri("https://download.samplelib.com/mp4/sample-15s.mp4")
                    exoPlayer.setMediaItem(mediaItem)
                }

        player?.playWhenReady = true

        player?.prepare()
    }

    companion object {
        fun newInstance() = PlayerFragment()
    }

}