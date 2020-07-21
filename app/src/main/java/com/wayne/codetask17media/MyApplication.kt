package com.wayne.codetask17media

import android.app.Application
import com.wayne.codetask17media.di.apiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        val modules = listOf(
            apiModule
        )

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(modules)
        }
    }
}