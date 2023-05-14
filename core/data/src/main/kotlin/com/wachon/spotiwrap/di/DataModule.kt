package com.wachon.spotiwrap.di

import com.wachon.spotiwrap.core.database.di.DatabaseModule
import com.wachon.spotiwrap.core.network.di.NetworkModule
import com.wachon.spotiwrap.data.ArtistsRepository
import com.wachon.spotiwrap.data.DefaultArtistsRepository
import com.wachon.spotiwrap.data.DefaultTracksRepository
import com.wachon.spotiwrap.data.DefaultUserRepository
import com.wachon.spotiwrap.data.TracksRepository
import com.wachon.spotiwrap.data.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val DataModule = module {
    includes(
        DatabaseModule,
        NetworkModule,
        DataDataModule,
        DataDomainModule,
        DataPresentationModule
    )
}

private val DataDataModule: Module
    get() = module {
        single<UserRepository> { DefaultUserRepository(get(), get()) }
        single<TracksRepository> { DefaultTracksRepository(get(), get()) }
        single<ArtistsRepository> { DefaultArtistsRepository(get(), get()) }
    }

private val DataDomainModule: Module
    get() = module {

    }

private val DataPresentationModule: Module
    get() = module {

    }