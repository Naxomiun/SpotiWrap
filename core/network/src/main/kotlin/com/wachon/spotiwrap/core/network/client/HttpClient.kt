package com.wachon.spotiwrap.core.network.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.cache.HttpCache
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.http.URLBuilder
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object HttpClient {

    @OptIn(ExperimentalSerializationApi::class)
    fun getClient(
        baseUrl: String,
        interceptors: List<Interceptor>
    ) = HttpClient(OkHttp) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = false
                    isLenient = true
                    explicitNulls = false
                }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
            socketTimeoutMillis = 15000L
        }

        install(HttpCache)

        install(DefaultRequest) {
            val endpointUrlBuilder = URLBuilder(baseUrl)
            url {
                host = endpointUrlBuilder.host
                protocol = endpointUrlBuilder.protocol
            }
        }

        engine {
            clientCacheSize = (10 * 1024 * 1024L).toInt()
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            addInterceptor(loggingInterceptor)
            interceptors.forEach {
                addInterceptor(it)
            }
        }
    }
}

private object CustomAndroidHttpLogger : Logger {
    private const val logTag = "CustomAndroidHttpLogger"

    override fun log(message: String) {
        Log.i(logTag, message)
    }
}