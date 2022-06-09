package com.gymix.videoplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gymix.videoplayer.databinding.PlayerFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayerFragment : Fragment() {

    private val viewModel: PlayerViewModel by viewModels()
    private lateinit var binding: PlayerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PlayerFragmentBinding.inflate(inflater)

        return binding.root
    }

    companion object {
        fun newInstance() = PlayerFragment()
    }

}