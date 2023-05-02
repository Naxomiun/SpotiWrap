package com.wachon.spotiwrap.core.network.interceptor

import com.wachon.spotiwrap.core.auth.config.GetAuthConfigUseCase
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    private val getAuthConfig: GetAuthConfigUseCase
) : Interceptor {

    companion object {
        private const val TOKEN_AUTH_KEY = "Authorization"
        private const val TOKEN_AUTH_VALUE = "Basic"

        private const val TOKEN_CHARSET_KEY = "Content-Type"
        private const val TOKEN_CHARSET_VALUE = "application/x-www-form-urlencoded"

        private const val TOKEN_TYPE_KEY = "Charset"
        private const val TOKEN_TYPE_VALUE = "UTF-8"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val authConfig = getAuthConfig()
        val original = chain.request()
        val requestBuilder = original.newBuilder()
            .addHeader(TOKEN_AUTH_KEY, "$TOKEN_AUTH_VALUE ${authConfig.clientBase64}")
            .addHeader(TOKEN_CHARSET_KEY, TOKEN_CHARSET_VALUE)
            .addHeader(TOKEN_TYPE_KEY, TOKEN_TYPE_VALUE)
            .url(original.url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}