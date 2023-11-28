package com.wachon.spotiwrap.features.tracks.di

import com.wachon.spotiwrap.features.tracks.domain.GetAlbum
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumTracks
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumTracksUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetAlbumUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistAlbums
import com.wachon.spotiwrap.features.tracks.domain.GetArtistAlbumsUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistRelated
import com.wachon.spotiwrap.features.tracks.domain.GetArtistRelatedUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistTopTracks
import com.wachon.spotiwrap.features.tracks.domain.GetArtistTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetArtistsFromTrack
import com.wachon.spotiwrap.features.tracks.domain.GetArtistsFromTrackUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetTrack
import com.wachon.spotiwrap.features.tracks.domain.GetTrackFeatures
import com.wachon.spotiwrap.features.tracks.domain.GetTrackFeaturesUseCase
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
        factoryOf(::GetArtistsFromTrack) bind GetArtistsFromTrackUseCase::class
        factoryOf(::GetTrack) bind GetTrackUseCase::class
        factoryOf(::GetTrackFeatures) bind GetTrackFeaturesUseCase::class
        factoryOf(::GetArtistAlbums) bind GetArtistAlbumsUseCase::class
        factoryOf(::GetArtistTopTracks) bind GetArtistTopTracksUseCase::class
        factoryOf(::GetArtistRelated) bind GetArtistRelatedUseCase::class
        factoryOf(::GetUserTopTracks) bind GetUserTopTracksUseCase::class
        factoryOf(::GetAlbum) bind GetAlbumUseCase::class
        factoryOf(::GetAlbumTracks) bind GetAlbumTracksUseCase::class
    }

private val TracksPresentationModule: Module
    get() = module {

    }