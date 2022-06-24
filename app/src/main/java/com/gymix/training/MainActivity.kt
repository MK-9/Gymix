package com.gymix.training

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gymix.player.PlayerService
import com.gymix.training.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy { createNavHostFragment() }
    private val navController: NavController by lazy { createNavController() }

    lateinit var binding: ActivityMainBinding

    private var playerService: PlayerService? = null
    private val serviceConnection: ServiceConnection by lazy { initServiceConnection() }
    private var serviceBind = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.slidingLayout.addPanelSlideListener(object :
            SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                binding.smallPlayerItem.root.alpha = 1f - slideOffset
                binding.bigPlayerItem.root.alpha = slideOffset
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    binding.bigPlayerItem.root.visibility = View.INVISIBLE
                } else if (newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                    binding.bigPlayerItem.root.visibility = View.VISIBLE
                }
            }
        })

        binding.smallPlayerItem.play.setOnClickListener {
            playAudio("https://upload.wikimedia.org/wikipedia/commons/6/6c/Grieg_Lyric_Pieces_Kobold.ogg");
        }

        binding.bigPlayerItem.play.setOnClickListener {
            playAudio("https://upload.wikimedia.org/wikipedia/commons/6/6c/Grieg_Lyric_Pieces_Kobold.ogg");
        }
    }

    private fun playAudio(mediaFile: String) {
        if (!serviceBind) {
            val intent = Intent(this, PlayerService::class.java)
            intent.putExtra("Media", mediaFile)
            startService(intent)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        } else {

        }
    }

    private fun createNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun createNavController(): NavController {
        return navHostFragment.navController
    }

    private fun initServiceConnection() = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceBind = true
            playerService = (service as PlayerService.PlayerBinder).getPlayerService()

            Toast.makeText(this@MainActivity, "Service Bound", Toast.LENGTH_SHORT).show()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            serviceBind = false

            Toast.makeText(this@MainActivity, "Service UnBound", Toast.LENGTH_SHORT).show()
        }
    }
}