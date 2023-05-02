package com.wachon.spotiwrap.core.network.model

import com.wachon.spotiwrap.core.common.model.TokenResponseModel
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
    fun toDomain(): TokenResponseModel {
        return TokenResponseModel(
            accessToken = this.accessToken ?: "",
            tokenType = this.tokenType ?: "",
            expiresIn = this.expiresIn ?: 0,
            refreshToken = this.refreshToken ?: "",
            scope = this.scope ?: ""
        )
    }
}