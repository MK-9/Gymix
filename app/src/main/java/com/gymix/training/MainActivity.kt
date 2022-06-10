package com.gymix.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gymix.training.databinding.ActivityMainBinding
import com.sothree.slidinguppanel.SlidingUpPanelLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy { createNavHostFragment() }
    private val navController: NavController by lazy { createNavController() }

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)

        binding.slidingLayout.addPanelSlideListener(object :
            SlidingUpPanelLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View?, slideOffset: Float) {
                binding.smallPlayerItem.alpha = 1f - slideOffset
                binding.bigPlayerItem.alpha = slideOffset
            }

            override fun onPanelStateChanged(
                panel: View?,
                previousState: SlidingUpPanelLayout.PanelState?,
                newState: SlidingUpPanelLayout.PanelState?
            ) {
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    binding.bigPlayerItem.visibility = View.INVISIBLE
                } else if (newState == SlidingUpPanelLayout.PanelState.DRAGGING) {
                    binding.bigPlayerItem.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun createNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun createNavController(): NavController {
        return navHostFragment.navController
    }
}