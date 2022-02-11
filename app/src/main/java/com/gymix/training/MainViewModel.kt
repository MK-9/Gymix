package com.gymix.training

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    var serviceConnection: ServiceConnection

    //
    private var _mBinder: MutableLiveData<MyBoundedService.MyBinder> = MutableLiveData()
    val mBinder: LiveData<MyBoundedService.MyBinder> = _mBinder

    //
    private var _mProgress: MutableLiveData<Int> = MutableLiveData()
    val mProgress: LiveData<Int> = _mProgress

    init {
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, iBinder: IBinder?) {
                Log.d("MyBoundedService", "onServiceConnected")

                _mBinder.postValue(iBinder as MyBoundedService.MyBinder)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("MyBoundedService", "onServiceDisconnected")

                _mBinder.postValue(null)
            }
        }
    }
}