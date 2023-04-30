package com.wachon.spotiwrap.features.login.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TokenService(
    private val httpClient: HttpClient
) {

    fun getAccessToken(): Flow<TokenResponse> = flow {
        emit(httpClient.get("/api/token").body())
    }

}