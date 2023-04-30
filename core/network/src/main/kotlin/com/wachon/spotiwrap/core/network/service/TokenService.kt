package com.wachon.spotiwrap.core.network.service

import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import com.wachon.spotiwrap.core.network.model.TokenResponseApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class TokenService(
    private val httpClient: HttpClient
) {
    suspend fun getAccessToken(
        code: String,
        authConfig: AuthConfig
    ): TokenResponseApi = httpClient.get("/api/token") {
        parameter("grant_type", "authorization_code")
        parameter("code", code)
        parameter("redirect_uri", authConfig.redirectUrl)
        parameter("client_id", authConfig.clientId)
        parameter("client_secret", authConfig.campaign)
    }.body()
}