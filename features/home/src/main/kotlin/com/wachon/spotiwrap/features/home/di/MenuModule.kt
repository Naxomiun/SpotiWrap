package com.wachon.spotiwrap.features.home.di

import com.wachon.spotiwrap.features.home.domain.GetTopGenresFromArtists
import com.wachon.spotiwrap.features.home.domain.GetUserTopGenresFromArtistsUseCase
import com.wachon.spotiwrap.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val MenuModule = module {
    includes(
        MenuDataModule,
        MenuDomainModule,
        MenuPresentationModule
    )
}

private val MenuDataModule: Module
    get() = module {

    }

private val MenuDomainModule: Module
    get() = module {
        factoryOf(::GetTopGenresFromArtists) bind GetUserTopGenresFromArtistsUseCase::class
    }

private val MenuPresentationModule: Module
    get() = module {
        viewModelOf(::HomeViewModel)
    }
