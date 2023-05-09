package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.auth.model.RefreshTokenModel
import com.wachon.spotiwrap.core.auth.model.TokenModel
import com.wachon.spotiwrap.core.persistence.sharedpreferences.DataProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.SharedPreferencesItem

interface TokenRepository {
    suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenModel
    suspend fun refreshToken(): TokenModel
    fun getPersistedRefreshToken(): String
    fun persistToken(token: TokenModel): TokenModel
    fun getPersistedToken(): TokenModel
    fun updatePersistedToken(token: RefreshTokenModel): TokenModel
}

class DefaultTokenRepository(
    private val tokenDataSource: TokenDatasource,
    private val dataProvider: DataProvider,
) : TokenRepository {

    override suspend fun getAccessToken(code: String, authConfig: AuthConfig): TokenModel {
        val token = tokenDataSource.getAccessToken(code = code, authConfig = authConfig).toDomain()
        return persistToken(token)
    }

    override suspend fun refreshToken(): TokenModel {
        val newToken = tokenDataSource.getRefreshToken(refreshToken = getPersistedRefreshToken()).toDomain()
        return updatePersistedToken(newToken)
    }

    override fun getPersistedRefreshToken(): String {
        return dataProvider.getString(SharedPreferencesItem.REFRESH_TOKEN, decrypt = true) ?: throw IllegalStateException("Refresh token not found")
    }

    override fun persistToken(token: TokenModel): TokenModel {
        dataProvider.setString(SharedPreferencesItem.TOKEN, token.accessToken, encrypt = true)
        dataProvider.setString(SharedPreferencesItem.REFRESH_TOKEN, token.refreshToken, encrypt = true)
        dataProvider.setLong(SharedPreferencesItem.TOKEN_EXPIRE_TIMESTAMP, token.expireTimestamp)

        return TokenModel(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            expireTimestamp = token.expireTimestamp
        )
    }

    override fun getPersistedToken(): TokenModel {
        val accessToken = dataProvider.getString(SharedPreferencesItem.TOKEN, decrypt = true)
        val refreshToken = dataProvider.getString(SharedPreferencesItem.REFRESH_TOKEN, decrypt = true)
        val expireTimestamp = dataProvider.getLong(SharedPreferencesItem.TOKEN_EXPIRE_TIMESTAMP)

        if(accessToken == null || refreshToken == null) {
            throw IllegalStateException("Token not found")
        }

        return TokenModel(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expireTimestamp = expireTimestamp
        )
    }

    override fun updatePersistedToken(token: RefreshTokenModel): TokenModel {
        dataProvider.setString(SharedPreferencesItem.TOKEN, token.accessToken, encrypt = true)
        dataProvider.setLong(SharedPreferencesItem.TOKEN_EXPIRE_TIMESTAMP, token.expireTimestamp)

        return TokenModel(
            accessToken = token.accessToken,
            refreshToken = getPersistedRefreshToken(),
            expireTimestamp = token.expireTimestamp
        )
    }

}