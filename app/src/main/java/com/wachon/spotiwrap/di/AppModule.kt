package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.features.login.di.LoginModule
import org.koin.core.module.Module
import org.koin.dsl.module

val FeaturesModule: Module get() = module {
    includes(
        LoginModule
    )
}
