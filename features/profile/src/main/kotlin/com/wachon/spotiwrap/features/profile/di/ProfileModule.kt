package com.wachon.spotiwrap.features.profile.di

import com.wachon.spotiwrap.features.profile.data.DefaultUserRepository
import com.wachon.spotiwrap.features.profile.data.UserRepository
import com.wachon.spotiwrap.features.profile.domain.GetUserProfile
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import org.koin.core.module.Module
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
        single<UserRepository> { DefaultUserRepository(get(), get()) }
    }

private val ProfileDomainModule: Module
    get() = module {
        factory<GetUserProfileUseCase> { GetUserProfile(get(), get()) }
    }

private val ProfilePresentationModule: Module
    get() = module {

    }