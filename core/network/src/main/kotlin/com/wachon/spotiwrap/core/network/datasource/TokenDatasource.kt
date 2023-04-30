package com.wachon.spotiwrap.core.network.datasource

import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import com.wachon.spotiwrap.core.network.model.TokenResponseApi
import com.wachon.spotiwrap.core.network.service.TokenService

interface TokenDatasource {
    suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenResponseApi
}

class DefaultTokenDatasource(
    private val tokenService: TokenService
) : TokenDatasource {

    override suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenResponseApi {
        return tokenService.getAccessToken(
            code = code,
            authConfig = authConfig
        )
    }
}