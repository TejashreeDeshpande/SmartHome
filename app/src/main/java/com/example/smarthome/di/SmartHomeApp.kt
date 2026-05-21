package com.example.smarthome.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SmartHomeApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@SmartHomeApp)
            modules(appModule)
        }
    }
}