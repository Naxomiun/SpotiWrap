package com.wachon.spotiwrap.features.menu.di

import com.wachon.spotiwrap.features.menu.domain.GetToken
import com.wachon.spotiwrap.features.menu.domain.GetTokenUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserProfile
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItems
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import com.wachon.spotiwrap.features.menu.presentation.categories.track.TrackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

    }

private val MenuDomainModule: Module
    get() = module {
        factory<GetUserProfileUseCase> { GetUserProfile(get()) }
        factory<GetUserTopItemsUseCase> { GetUserTopItems(get()) }
        factory<GetTokenUseCase> { GetToken(get()) }
    }

private val MenuPresentationModule: Module
    get() = module {
        viewModel { MenuViewModel(get(), get(), get()) }
        viewModel { TrackViewModel(get(), get()) }
    }
