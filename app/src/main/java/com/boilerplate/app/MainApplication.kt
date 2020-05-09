package com.boilerplate.app

import android.app.Application
import com.boilerplate.app.view.di.moduleApp
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Main Application
 */
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(moduleApp)
        }
    }
}