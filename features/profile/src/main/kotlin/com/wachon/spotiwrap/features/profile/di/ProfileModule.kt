package com.wachon.spotiwrap.features.profile.di

import com.wachon.spotiwrap.features.profile.domain.GetUserPlaylists
import com.wachon.spotiwrap.features.profile.domain.GetUserPlaylistsUseCase
import com.wachon.spotiwrap.features.profile.domain.GetUserProfile
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.profilescreen.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val ProfileModule = module {
    includes(
        ProfileDataModule,
        ProfileDomainModule,
        ProfilePresentationModule
    )
}

private val ProfileDataModule: Module
    get() = module {

    }

private val ProfileDomainModule: Module
    get() = module {
        factoryOf(::GetUserProfile) bind GetUserProfileUseCase::class
        factoryOf(::GetUserPlaylists) bind GetUserPlaylistsUseCase::class
    }

private val ProfilePresentationModule: Module
    get() = module {
        viewModelOf(::ProfileViewModel)
    }