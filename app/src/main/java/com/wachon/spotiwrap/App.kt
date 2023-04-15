package com.wachon.spotiwrap

import android.app.Application
import com.wachon.spotiwrap.di.CoreModules
import com.wachon.spotiwrap.di.FeaturesModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                CoreModules,
                FeaturesModule
            )
        }
    }

}