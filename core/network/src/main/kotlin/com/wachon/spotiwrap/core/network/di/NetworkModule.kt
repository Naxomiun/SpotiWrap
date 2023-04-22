package com.wachon.spotiwrap.core.network.di

import com.wachon.spotiwrap.core.network.interceptors.ThreadInterceptor
import com.wachon.spotiwrap.core.network.interceptors.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL: String = "https://api.spotify.com"

val NetworkModule: Module
    get() = module {
        factory { ThreadInterceptor() }
        factory { TokenInterceptor(get()) }
        factory {
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(get<ThreadInterceptor>())
                .addNetworkInterceptor(get<TokenInterceptor>())
                .build()
        }
        single {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        }
    }
