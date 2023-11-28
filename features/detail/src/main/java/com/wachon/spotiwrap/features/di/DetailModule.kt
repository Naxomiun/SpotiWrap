package com.wachon.spotiwrap.features.di

import com.wachon.spotiwrap.features.presentation.album.AlbumViewModel
import com.wachon.spotiwrap.features.presentation.artist.ArtistViewModel
import com.wachon.spotiwrap.features.presentation.track.TrackViewModel

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val DetailModule = module {
    includes(
        DetailDataModule,
        DetailDomainModule,
        DetailPresentationModule
    )
}

private val DetailDataModule: Module
    get() = module {

    }

private val DetailDomainModule: Module
    get() = module {
    }

private val DetailPresentationModule: Module
    get() = module {
        viewModelOf(::AlbumViewModel)
        viewModelOf(::ArtistViewModel)
        viewModelOf(::TrackViewModel)
    }