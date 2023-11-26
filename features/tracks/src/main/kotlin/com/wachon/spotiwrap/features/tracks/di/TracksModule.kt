package com.wachon.spotiwrap.features.tracks.di

import com.wachon.spotiwrap.features.tracks.domain.GetTrack
import com.wachon.spotiwrap.features.tracks.domain.GetTrackUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
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
        factoryOf(::GetTrack) bind GetTrackUseCase::class
        factoryOf(::GetUserTopTracks) bind GetUserTopTracksUseCase::class
    }

private val TracksPresentationModule: Module
    get() = module {

    }