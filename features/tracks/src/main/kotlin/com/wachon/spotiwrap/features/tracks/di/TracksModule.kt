package com.wachon.spotiwrap.features.tracks.di

import com.wachon.spotiwrap.features.tracks.data.DefaultTracksRepository
import com.wachon.spotiwrap.features.tracks.data.TracksRepository
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.presentation.toptracks.TopTracksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
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
        single<TracksRepository> { DefaultTracksRepository(get()) }
    }

private val TracksDomainModule: Module
    get() = module {
        factory<GetUserTopTracksUseCase> { GetUserTopTracks(get(), get()) }
    }

private val TracksPresentationModule: Module
    get() = module {
        viewModelOf(::TopTracksViewModel)
    }