package com.wachon.spotiwrap.features.menu.di

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.domain.GetUserProfile
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItems
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
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
    }

private val MenuDomainModule: Module
    get() = module {
        factory<GetUserProfileUseCase> { GetUserProfile(get(), get()) }
        factory<GetUserTopItemsUseCase> { GetUserTopItems(get(), get()) }
    }

private val MenuPresentationModule: Module
    get() = module {
        viewModelOf(::MenuViewModel)
        viewModelOf(::TrackViewModel)
    }
