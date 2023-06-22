package com.gymix.training

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gymix.training.alarm.ReminderService
import com.gymix.training.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navHostFragment by lazy { createNavHostFragment() }
    private val navController: NavController by lazy { createNavController() }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
//
//        binding.login.setOnClickListener {
//            ReminderService.execute(this@MainActivity, ReminderService.ACTION_SET_ALARM, 1)
//        }
//
//        binding.logout.setOnClickListener {
//            ReminderService.execute(this@MainActivity, ReminderService.ACTION_CANCEL_ALARM, 1)
//        }
    }

    private fun createNavHostFragment(): NavHostFragment {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    private fun createNavController(): NavController {
        return navHostFragment.navController
    }
}