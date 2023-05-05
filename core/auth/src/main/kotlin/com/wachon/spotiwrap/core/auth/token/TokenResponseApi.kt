package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.auth.model.RefreshTokenModel
import com.wachon.spotiwrap.core.auth.model.TokenModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponseApi(
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("token_type") val tokenType: String? = null,
    @SerialName("expires_in") val expiresIn: Int? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
    @SerialName("scope") val scope: String? = null
) {
    fun toDomain(): TokenModel {
        return TokenModel(
            accessToken = this.accessToken ?: "",
            expireTimestamp = System.currentTimeMillis() + (this.expiresIn ?: 0) * 1000L,
            refreshToken = this.refreshToken ?: ""
        )
    }
}

@Serializable
data class RefreshTokenApi(
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("token_type") val tokenType: String? = null,
    @SerialName("expires_in") val expiresIn: Int? = null,
    @SerialName("scope") val scope: String? = null
) {
    fun toDomain(): RefreshTokenModel {
        return RefreshTokenModel(
            accessToken = this.accessToken ?: "",
            expireTimestamp = System.currentTimeMillis() + (this.expiresIn ?: 0) * 1000L
        )
    }
}