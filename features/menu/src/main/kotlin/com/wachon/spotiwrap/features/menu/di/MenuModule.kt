package com.wachon.spotiwrap.features.menu.di

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.datasource.DefaultNetworkSpotifyDatasource
import com.wachon.spotiwrap.features.menu.data.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.features.menu.data.repository.ArtistsRepository
import com.wachon.spotiwrap.features.menu.data.repository.DefaultArtistsRepository
import com.wachon.spotiwrap.features.menu.data.repository.DefaultTracksRepository
import com.wachon.spotiwrap.features.menu.data.repository.DefaultUserRepository
import com.wachon.spotiwrap.features.menu.data.repository.TracksRepository
import com.wachon.spotiwrap.features.menu.data.repository.UserRepository
import com.wachon.spotiwrap.features.menu.domain.GetUserProfile
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopArtists
import com.wachon.spotiwrap.features.menu.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracks
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import com.wachon.spotiwrap.features.menu.presentation.categories.track.TrackViewModel
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
        single { SpotifyService(get()) }
        single<NetworkSpotifyDatasource> { DefaultNetworkSpotifyDatasource(get()) }
        single<UserRepository> { DefaultUserRepository(get()) }
        single<TracksRepository> { DefaultTracksRepository(get()) }
        single<ArtistsRepository> { DefaultArtistsRepository(get()) }
    }

private val MenuDomainModule: Module
    get() = module {
        factory<GetUserProfileUseCase> { GetUserProfile(get(), get()) }
        factory<GetUserTopTracksUseCase> { GetUserTopTracks(get(), get()) }
        factory<GetUserTopArtistsUseCase> { GetUserTopArtists(get(), get()) }
    }

private val MenuPresentationModule: Module
    get() = module {
        viewModelOf(::MenuViewModel)
        viewModelOf(::TrackViewModel)
    }
