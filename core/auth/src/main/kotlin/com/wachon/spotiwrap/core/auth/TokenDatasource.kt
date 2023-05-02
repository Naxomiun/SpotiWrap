package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.auth.token.RefreshTokenApi
import com.wachon.spotiwrap.core.auth.token.TokenResponseApi

interface TokenDatasource {
    suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenResponseApi
    suspend fun getRefreshToken(refreshToken: String): RefreshTokenApi
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

    override suspend fun getRefreshToken(refreshToken: String): RefreshTokenApi {
        return tokenService.getRefreshToken(refreshToken = refreshToken)
    }
}