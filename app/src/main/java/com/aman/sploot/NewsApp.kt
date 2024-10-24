package com.aman.sploot

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApp:Application(){

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"On Create")
    }
    companion object{
        const val TAG = "Sploot News App Assignment"
    }
}
