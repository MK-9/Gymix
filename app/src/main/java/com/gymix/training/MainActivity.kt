package com.gymix.training

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gymix.training.databinding.ActivityMainBinding
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
    }

    private fun createNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun createNavController(): NavController {
        return navHostFragment.navController
    }
}