package com.wachon.spotiwrap.features.login.data

import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import com.wachon.spotiwrap.core.common.model.TokenResponseModel
import com.wachon.spotiwrap.core.network.datasource.TokenDatasource

interface TokenRepository {
    suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenResponseModel
    suspend fun getRefreshToken(refreshToken: String): TokenResponseModel
}

class DefaultTokenRepository(
    private val tokenDataSource: TokenDatasource
) : TokenRepository {

    override suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenResponseModel {
        return tokenDataSource.getAccessToken(code = code, authConfig = authConfig).toDomain()
    }

    override suspend fun getRefreshToken(refreshToken: String): TokenResponseModel {
        return tokenDataSource.getRefreshToken(refreshToken = refreshToken).toDomain()
    }

}