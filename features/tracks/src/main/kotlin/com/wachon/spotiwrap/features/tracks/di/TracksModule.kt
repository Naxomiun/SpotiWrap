package com.wachon.spotiwrap.features.tracks.di

import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val TracksModule = module {
    includes(
        TracksDataModule,
        TracksDomainModule,
        TracksPresentationModule
    )
}

private val TracksDataModule: Module
    get() = module {

    }

private val TracksDomainModule: Module
    get() = module {
        factory<GetUserTopTracksUseCase> { GetUserTopTracks(get(), get()) }
    }

private val TracksPresentationModule: Module
    get() = module {

    }