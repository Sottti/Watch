package com.sotti.watch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal class WatchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WatchApp)
        }
    }
}