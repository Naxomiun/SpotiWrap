package com.wachon.spotiwrap.features.login.data

import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TokenService(
    private val httpClient: HttpClient
) {

    fun getAccessToken(code: String, authConfig: AuthConfig): Flow<TokenResponse> = flow {
        emit(
            httpClient.get("/api/token") {
                header("Content-Type", "application/x-www-form-urlencoded")
                header("Charset", "UTF-8")
                parameter("grant_type", "authorization_code")
                parameter("code", code)
                parameter("redirect_uri", authConfig.redirectUrl)
                parameter("client_id", authConfig.clientId)
                parameter("client_secret", authConfig.campaign)
            }.body()
        )
    }

}