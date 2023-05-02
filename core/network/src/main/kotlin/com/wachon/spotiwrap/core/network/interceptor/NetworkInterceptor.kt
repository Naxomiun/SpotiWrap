package com.wachon.spotiwrap.core.network.interceptor

import com.wachon.spotiwrap.core.auth.token.GetTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor(
    private val getToken: GetTokenUseCase
) : Interceptor {

    companion object {
        private const val TOKEN_HEADER_KEY = "Authorization"
        private const val TOKEN_HEADER_VALUE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking { getToken() }
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader(TOKEN_HEADER_KEY, "$TOKEN_HEADER_VALUE ${token.accessToken}")
            .url(original.url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}