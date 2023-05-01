package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.client.HttpClient
import com.wachon.spotiwrap.core.network.datasource.DefaultNetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.datasource.DefaultTokenDatasource
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.datasource.TokenDatasource
import com.wachon.spotiwrap.core.network.interceptor.NetworkInterceptor
import com.wachon.spotiwrap.core.network.interceptor.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptor.TokenInterceptor
import com.wachon.spotiwrap.core.network.service.SpotifyService
import com.wachon.spotiwrap.core.network.service.TokenService
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val TOKEN_URL: String = "https://accounts.spotify.com"
private const val BASE_URL: String = "https://api.spotify.com"

val TokenModule: Module
    get() = module {
        factory { ThreadInterceptor() }
        factory { TokenInterceptor(get()) }
        single(named("TokenModule")) {
            HttpClient.getClient(
                baseUrl = TOKEN_URL,
                interceptors = listOf(
                    get<ThreadInterceptor>(),
                    get<TokenInterceptor>()
                )
            )
        }
        single { TokenService(get(named("TokenModule"))) }
        single<TokenDatasource> { DefaultTokenDatasource(get()) }
    }

val NetworkModule: Module
    get() = module {
        factory { ThreadInterceptor() }
        factory { NetworkInterceptor(get()) }
        single(named("NetworkModule")) {
            HttpClient.getClient(
                baseUrl = BASE_URL,
                interceptors = listOf(
                    get<ThreadInterceptor>(),
                    get<NetworkInterceptor>()
                )
            )
        }
        single { SpotifyService(get(named("NetworkModule"))) }
        single<NetworkSpotifyDatasource> { DefaultNetworkSpotifyDatasource(get()) }
    }
