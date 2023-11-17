package com.wachon.spotiwrap.features.recently.di

import com.wachon.spotiwrap.features.recently.domain.GetRecentlyPlayed
import com.wachon.spotiwrap.features.recently.domain.GetRecentlyPlayedUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val RecentlyModule = module {
    includes(
        RecentlyDataModule,
        RecentlyDomainModule,
        RecentlyPresentationModule,
    )
}

private val RecentlyDataModule: Module
    get() = module {

    }

private val RecentlyDomainModule: Module
    get() = module {
        factory<GetRecentlyPlayedUseCase> { GetRecentlyPlayed(get(), get()) }
    }

private val RecentlyPresentationModule: Module
    get() = module {

    }