package com.wachon.spotiwrap.core.network.interceptors

import com.wachon.spotiwrap.core.auth.token.GetTokenUseCase
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val getToken: GetTokenUseCase
) : Interceptor {

    companion object {
        private const val TOKEN_HEADER_KEY = "Authorization"
        private const val TOKEN_HEADER_VALUE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getToken()
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader(TOKEN_HEADER_KEY, "$TOKEN_HEADER_VALUE $token")
            .url(original.url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}