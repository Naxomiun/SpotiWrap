package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.clients.HttpClient
import com.wachon.spotiwrap.core.network.interceptors.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptors.TokenInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module

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
    }
