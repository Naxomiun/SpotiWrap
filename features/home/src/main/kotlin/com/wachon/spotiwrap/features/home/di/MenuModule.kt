package com.wachon.spotiwrap.features.home.di

import com.wachon.spotiwrap.features.home.domain.GetTopGenresFromArtists
import com.wachon.spotiwrap.features.home.domain.GetUserTopGenresFromArtistsUseCase
import com.wachon.spotiwrap.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
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
        factory<GetUserTopGenresFromArtistsUseCase>{ GetTopGenresFromArtists(get()) }
    }

private val MenuPresentationModule: Module
    get() = module {
        viewModelOf(::HomeViewModel)
    }
