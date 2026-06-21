package com.danielyan.gopeak

import android.app.Application
import org.koin.android.ext.koin.androidContext

class GoPeakApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
        }
    }
}