package com.wachon.spotiwrap.core.network.interceptor

import com.wachon.spotiwrap.core.auth.model.TokenModel
import com.wachon.spotiwrap.core.auth.token.GetTokenUseCase
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.net.HttpURLConnection

class NetworkInterceptor(
    private val getToken: GetTokenUseCase
) : Interceptor {

    companion object {
        private const val TOKEN_HEADER_KEY = "Authorization"
        private const val TOKEN_HEADER_VALUE = "Bearer"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val token = runBlocking { getToken() }

        val response = chain.proceed(requestWithAccessToken(token, request))

        if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
            response.close()
            val newAccessToken = runBlocking { getToken(refresh = true) }
            return chain.proceed(requestWithAccessToken(newAccessToken, request))
        }

        return response
    }

    private fun requestWithAccessToken(token: TokenModel, request: Request): Request =
        request.newBuilder()
            .addHeader(TOKEN_HEADER_KEY, "$TOKEN_HEADER_VALUE ${token.accessToken}")
            .url(request.url)
            .build()

}