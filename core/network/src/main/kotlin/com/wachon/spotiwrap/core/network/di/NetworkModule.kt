package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.clients.HttpClient
import com.wachon.spotiwrap.core.network.datasource.DefaultNetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.interceptors.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptors.TokenInterceptor
import com.wachon.spotiwrap.core.network.service.SpotifyService
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.dsl.single

private const val BASE_URL: String = "https://api.spotify.com"

val NetworkModule: Module
    get() = module {
        factory { ThreadInterceptor() }
        factory { TokenInterceptor(get()) }
        factory {
            HttpClient.getClient(
                baseUrl = BASE_URL,
                interceptors = listOf(
                    get<ThreadInterceptor>(),
                    get<TokenInterceptor>()
                )
            )
        }
        single { SpotifyService(get()) }
        single<NetworkSpotifyDatasource> { DefaultNetworkSpotifyDatasource(get()) }
    }
