package com.gymix.training

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.gymix.training.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    var service: MyBoundedService? = null
    private var binder: MyBoundedService.MyBinder? = null
    lateinit var runnable: Runnable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //create binding
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        //
        viewModel.mBinder.observe(this, { myBinder ->
            service = myBinder?.getService()
        })

        binding.startBtn.setOnClickListener {
            service?.startService()

            val handler = Handler()
            runnable = Runnable {
                service?.run {
                    if (getProgress() < finishProgress) {
                        binding.myProgress.progress = getProgress().toInt()
                        handler.postDelayed(runnable, 100)
                    } else {
                        handler.removeCallbacks(runnable)
                    }
                }
            }
            handler.postDelayed(runnable, 100)
        }

        binding.stopBtn.setOnClickListener {
            val boundedService = Intent(this, MyBoundedService::class.java)

            stopService(boundedService)
        }

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

    private fun startService() {
        val boundedService = Intent(this, MyBoundedService::class.java)
        startService(boundedService)
    }

    private fun bindService() {
        val boundedService = Intent(this, MyBoundedService::class.java)
        bindService(boundedService, viewModel.serviceConnection, BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()

        startService()

        bindService()
    }
}