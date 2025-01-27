package com.example.chatapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class BaseApplication @Inject constructor() : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
             Timber.plant(Timber.DebugTree())
        }
    }
}