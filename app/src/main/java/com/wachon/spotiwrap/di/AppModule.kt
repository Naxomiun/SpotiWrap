package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.core.auth.di.AuthModule
import com.wachon.spotiwrap.core.common.dispatchers.DispatchersModule
import com.wachon.spotiwrap.core.database.di.DatabaseModule
import com.wachon.spotiwrap.core.network.di.NetworkModule
import com.wachon.spotiwrap.core.persistence.di.PersistenceModule
import com.wachon.spotiwrap.data.di.DataModule
import com.wachon.spotiwrap.data.worker.SyncWorker
import com.wachon.spotiwrap.features.artists.di.ArtistsModule
import com.wachon.spotiwrap.features.login.di.LoginModule
import com.wachon.spotiwrap.features.home.di.MenuModule
import com.wachon.spotiwrap.features.profile.di.ProfileModule
import com.wachon.spotiwrap.features.splash.di.SplashModule
import com.wachon.spotiwrap.features.tracks.di.TracksModule
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.Module
import org.koin.dsl.module

val FeaturesModule: Module get() = module {
    includes(
        listOf(
            LoginModule,
            MenuModule,
            SplashModule,
            TracksModule,
            ArtistsModule,
            ProfileModule
        )
    )
}

val CoreModules: Module get() = module {
    includes(
        listOf(
            AuthModule,
            DispatchersModule,
            PersistenceModule,
            DataModule,
            NetworkModule,
            DatabaseModule
        )
    )
}


val WorkerModule: Module get() = module {
    workerOf(::SyncWorker)
}