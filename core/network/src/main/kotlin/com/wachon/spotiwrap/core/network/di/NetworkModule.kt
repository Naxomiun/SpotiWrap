package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.clients.HttpClient
import com.wachon.spotiwrap.core.network.interceptors.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptors.TokenInterceptor
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
    }

val NetworkModule: Module
    get() = module {
        factory { ThreadInterceptor() }
        factory { TokenInterceptor(get()) }
        single(named("NetworkModule")) {
            HttpClient.getClient(
                baseUrl = BASE_URL,
                interceptors = listOf(
                    get<ThreadInterceptor>(),
                    get<TokenInterceptor>()
                )
            )
        }
    }
