package com.wachon.spotiwrap.features.artists.di

import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
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
        factoryOf(::GetUserTopArtists) bind GetUserTopArtistsUseCase::class
    }

private val ArtistsPresentationModule: Module
    get() = module {

    }