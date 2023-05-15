package com.wachon.spotiwrap.data.di

import com.wachon.spotiwrap.core.database.di.DatabaseModule
import com.wachon.spotiwrap.core.network.di.NetworkModule
import com.wachon.spotiwrap.data.repository.ArtistsRepository
import com.wachon.spotiwrap.data.repository.DefaultArtistsRepository
import com.wachon.spotiwrap.data.repository.DefaultTracksRepository
import com.wachon.spotiwrap.data.repository.DefaultUserRepository
import com.wachon.spotiwrap.data.repository.TracksRepository
import com.wachon.spotiwrap.data.repository.UserRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val DataModule = module {
    includes(
        DatabaseModule,
        NetworkModule,
        DataDataModule
    )
}

private val DataDataModule: Module
    get() = module {
        single<UserRepository> { DefaultUserRepository(get(), get()) }
        single<TracksRepository> { DefaultTracksRepository(get(), get()) }
        single<ArtistsRepository> { DefaultArtistsRepository(get(), get()) }
    }