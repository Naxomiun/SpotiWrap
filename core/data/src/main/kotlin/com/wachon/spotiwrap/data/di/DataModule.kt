package com.wachon.spotiwrap.data.di

import com.wachon.spotiwrap.data.repository.ArtistsRepository
import com.wachon.spotiwrap.data.repository.DefaultArtistsRepository
import com.wachon.spotiwrap.data.repository.DefaultTracksRepository
import com.wachon.spotiwrap.data.repository.DefaultUserRepository
import com.wachon.spotiwrap.data.repository.TracksRepository
import com.wachon.spotiwrap.data.repository.UserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val DataModule = module {
    singleOf(::DefaultUserRepository) bind UserRepository::class
    singleOf(::DefaultTracksRepository) bind TracksRepository::class
    singleOf(::DefaultArtistsRepository) bind ArtistsRepository::class
}
