package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.features.login.di.LoginModule
import com.wachon.spotiwrap.features.menu.di.MenuModule
import com.wachon.spotiwrap.features.menu.di.NetworkModule
import org.koin.core.module.Module
import org.koin.dsl.module

val FeaturesModule: Module get() = module {
    includes(
        listOf(
            LoginModule,
            MenuModule,
            NetworkModule
        )
    )
}
