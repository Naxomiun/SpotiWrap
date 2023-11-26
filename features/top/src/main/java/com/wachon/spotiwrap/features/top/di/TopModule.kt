package com.wachon.spotiwrap.features.top.di

import com.wachon.spotiwrap.features.top.domain.GetArtists
import com.wachon.spotiwrap.features.top.domain.GetArtistsUseCase
import com.wachon.spotiwrap.features.top.domain.GetTracks
import com.wachon.spotiwrap.features.top.domain.GetTracksUseCase
import com.wachon.spotiwrap.features.top.presentation.TopViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val TopModule = module {
    includes(
        TopDataModule,
        TopDomainModule,
        TopPresentationModule
    )
}


private val TopDataModule: Module
    get() = module {

    }

private val TopDomainModule: Module
    get() = module {
        factoryOf(::GetArtists) bind GetArtistsUseCase::class
        factoryOf(::GetTracks) bind GetTracksUseCase::class
    }

private val TopPresentationModule: Module
    get() = module {
        viewModelOf(::TopViewModel)
    }