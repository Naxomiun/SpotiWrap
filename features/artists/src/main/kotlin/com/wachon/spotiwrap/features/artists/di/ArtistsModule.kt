package com.wachon.spotiwrap.features.artists.di

import com.wachon.spotiwrap.features.artists.domain.GetArtist
import com.wachon.spotiwrap.features.artists.domain.GetArtistUseCase
import com.wachon.spotiwrap.features.artists.domain.GetTopGenresFromArtists
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.artists.domain.GetUserTopGenresFromArtistsUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ArtistsModule = module {
    includes(
        ArtistsDataModule,
        ArtistsDomainModule,
        ArtistsPresentationModule
    )
}

private val ArtistsDataModule: Module
    get() = module {

    }

private val ArtistsDomainModule: Module
    get() = module {
        factoryOf(::GetArtist) bind GetArtistUseCase::class
        factoryOf(::GetUserTopArtists) bind GetUserTopArtistsUseCase::class
        factoryOf(::GetTopGenresFromArtists) bind GetUserTopGenresFromArtistsUseCase::class
    }

private val ArtistsPresentationModule: Module
    get() = module {

    }