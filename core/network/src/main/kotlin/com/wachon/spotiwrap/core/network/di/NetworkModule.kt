package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.client.HttpClient
import com.wachon.spotiwrap.core.network.datasource.DefaultNetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.interceptor.NetworkInterceptor
import com.wachon.spotiwrap.core.network.interceptor.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptor.TokenInterceptor
import com.wachon.spotiwrap.core.network.service.SpotifyService
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val TOKEN_URL: String = "https://accounts.spotify.com"
private const val BASE_URL: String = "https://api.spotify.com"

val NetworkModule = module {
    factory { ThreadInterceptor() }
    factory { NetworkInterceptor(get()) }
    factory { TokenInterceptor(get()) }

    single(named("UserClient")) {
        HttpClient.getClient(
            baseUrl = BASE_URL,
            interceptors = listOf(
                get<ThreadInterceptor>(),
                get<NetworkInterceptor>()
            ),
        )
    }

    single(named("AuthClient")) {
        HttpClient.getClient(
            baseUrl = TOKEN_URL,
            interceptors = listOf(
                get<ThreadInterceptor>(),
                get<TokenInterceptor>()
            )
        )
    }

    single { SpotifyService(get(named("UserClient"))) }
    single<NetworkSpotifyDatasource> { DefaultNetworkSpotifyDatasource(get()) }
}
