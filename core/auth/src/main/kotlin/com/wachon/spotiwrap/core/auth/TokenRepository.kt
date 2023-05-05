package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.auth.model.RefreshTokenModel
import com.wachon.spotiwrap.core.auth.model.TokenModel
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

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
    private val encryptedDataProvider: EncryptedDataProvider,
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
        return encryptedDataProvider.getEncryptedString(EncryptedItem.REFRESH_TOKEN) ?: throw IllegalStateException("Refresh token not found")
    }

    override fun persistToken(token: TokenModel): TokenModel {
        encryptedDataProvider.setEncryptedString(EncryptedItem.TOKEN, token.accessToken)
        encryptedDataProvider.setEncryptedString(EncryptedItem.REFRESH_TOKEN, token.refreshToken)
        encryptedDataProvider.setEncryptedLong(EncryptedItem.TOKEN_EXPIRE_TIMESTAMP, token.expireTimestamp)

        return TokenModel(
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            expireTimestamp = token.expireTimestamp
        )
    }

    override fun getPersistedToken(): TokenModel {
        val accessToken = encryptedDataProvider.getEncryptedString(EncryptedItem.TOKEN)
        val refreshToken = encryptedDataProvider.getEncryptedString(EncryptedItem.REFRESH_TOKEN)
        val expireTimestamp = encryptedDataProvider.getEncryptedLong(EncryptedItem.TOKEN_EXPIRE_TIMESTAMP)

        if(accessToken == null || refreshToken == null || expireTimestamp == null) {
            throw IllegalStateException("Token not found")
        }

        return TokenModel(
            accessToken = accessToken,
            refreshToken = refreshToken,
            expireTimestamp = expireTimestamp
        )
    }

    override fun updatePersistedToken(token: RefreshTokenModel): TokenModel {
        encryptedDataProvider.setEncryptedString(EncryptedItem.TOKEN, token.accessToken)
        encryptedDataProvider.setEncryptedLong(EncryptedItem.TOKEN_EXPIRE_TIMESTAMP, token.expireTimestamp)

        return TokenModel(
            accessToken = token.accessToken,
            refreshToken = getPersistedRefreshToken(),
            expireTimestamp = token.expireTimestamp
        )
    }

}