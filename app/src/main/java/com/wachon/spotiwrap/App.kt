package com.wachon.spotiwrap

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import com.wachon.spotiwrap.data.worker.SyncWorker
import com.wachon.spotiwrap.di.CoreModules
import com.wachon.spotiwrap.di.FeaturesModule
import com.wachon.spotiwrap.di.WorkerModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager

class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            workManagerFactory()
            modules(
                CoreModules,
                FeaturesModule,
                WorkerModule
            )
        }

        Sync.initialize(this)
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .respectCacheHeaders(false)
            .diskCache(
                DiskCache.Builder()
                    .directory(cacheDir.resolve("coil"))
                    .maxSizeBytes(128L * 1024L * 1024L)
                    .build()
            )
            .build()
    }

}

object Sync {
    fun initialize(context: Context) {
        WorkManager
            .getInstance(context)
            .enqueueUniqueWork(
                SyncWorkName,
                ExistingWorkPolicy.KEEP,
                SyncWorker.startUpSyncWork(),
            )
    }
}


internal const val SyncWorkName = "SyncWorkName"
