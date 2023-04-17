package com.wachon.spotiwrap.features.splash.di

import com.wachon.spotiwrap.features.splash.presentation.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val SplashModule = module {
    includes(
        SplashDataModule,
        SplashDomainModule,
        SplashPresentationModule
    )
}

private val SplashDataModule: Module
    get() = module {

    }

private val SplashDomainModule: Module
    get() = module {

    }

private val SplashPresentationModule: Module
    get() = module {
        viewModelOf(::SplashViewModel)
    }