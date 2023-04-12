package com.wachon.spotiwrap.features.menu.di

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val NetworkModule = module {
    single { retrofit }
    single { get<Retrofit>().create(SpotifyService::class.java) }
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.spotify.com")
    .addConverterFactory(GsonConverterFactory.create())
    .client(
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    )
    .build()