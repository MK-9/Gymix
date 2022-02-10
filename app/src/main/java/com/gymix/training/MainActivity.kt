package com.gymix.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gymix.training.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create binding
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val playerService = Intent(this, MyPlayerService::class.java)

        binding.startBtn.setOnClickListener {
            ContextCompat.startForegroundService(this@MainActivity, playerService)
        }

        binding.stopBtn.setOnClickListener { stopService(playerService) }

        binding.twoBtn.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    SecondActivity::class.java
                )
            )
        }

//        //create navHostFragment
//        navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        //create navController
//        navController = navHostFragment.navController
//
//        //link bottom navigation to fragments
//        binding.bottomNavigation.setupWithNavController(navController)
    }


}