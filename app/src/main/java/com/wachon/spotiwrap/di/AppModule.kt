package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.core.auth.AuthModule
import com.wachon.spotiwrap.core.network.di.NetworkModule
import com.wachon.spotiwrap.core.persistence.di.PersistenceModule
import com.wachon.spotiwrap.features.login.di.LoginModule
import com.wachon.spotiwrap.features.menu.di.MenuModule
import org.koin.core.module.Module
import org.koin.dsl.module

val FeaturesModule: Module get() = module {
    includes(
        listOf(
            LoginModule,
            MenuModule,
        )
    )
}

val CoreModules: Module get() = module {
    includes(
        listOf(
            AuthModule,
            NetworkModule,
            PersistenceModule
        )
    )
}
