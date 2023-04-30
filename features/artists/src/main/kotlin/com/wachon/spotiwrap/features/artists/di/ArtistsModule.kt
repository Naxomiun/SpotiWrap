package com.wachon.spotiwrap.features.artists.di

import com.wachon.spotiwrap.features.artists.data.ArtistsRepository
import com.wachon.spotiwrap.features.artists.data.DefaultArtistsRepository
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.artists.presentation.topartists.TopArtistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
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
        single<ArtistsRepository> { DefaultArtistsRepository(get()) }
    }

private val ArtistsDomainModule: Module
    get() = module {
        factory<GetUserTopArtistsUseCase> { GetUserTopArtists(get(), get()) }
    }

private val ArtistsPresentationModule: Module
    get() = module {
        viewModelOf(::TopArtistsViewModel)
    }