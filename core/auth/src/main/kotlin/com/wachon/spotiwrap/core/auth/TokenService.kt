package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.auth.token.RefreshTokenApi
import com.wachon.spotiwrap.core.auth.token.TokenResponseApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post

class TokenService(
    private val httpClient: HttpClient
) {

    suspend fun getAccessToken(
        code: String,
        authConfig: AuthConfig
    ): TokenResponseApi = httpClient.post("/api/token") {
        parameter("grant_type", "authorization_code")
        parameter("code", code)
        parameter("redirect_uri", authConfig.redirectUrl)
        parameter("client_id", authConfig.clientId)
        parameter("client_secret", authConfig.campaign)
    }.body()

    suspend fun getRefreshToken(
        refreshToken: String
    ): RefreshTokenApi = httpClient.post("/api/token") {
        parameter("grant_type", "refresh_token")
        parameter("refresh_token", refreshToken)
    }.body()

}