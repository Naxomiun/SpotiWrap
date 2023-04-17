package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.core.auth.AuthModule
import com.wachon.spotiwrap.core.common.dispatchers.DispatchersModule
import com.wachon.spotiwrap.core.network.di.NetworkModule
import com.wachon.spotiwrap.core.persistence.di.PersistenceModule
import com.wachon.spotiwrap.features.login.di.LoginModule
import com.wachon.spotiwrap.features.menu.di.MenuModule
import com.wachon.spotiwrap.features.splash.di.SplashModule
import org.koin.core.module.Module
import org.koin.dsl.module

val FeaturesModule: Module get() = module {
    includes(
        listOf(
            LoginModule,
            MenuModule,
            SplashModule
        )
    )
}

val CoreModules: Module get() = module {
    includes(
        listOf(
            DispatchersModule,
            AuthModule,
            NetworkModule,
            PersistenceModule
        )
    )
}
