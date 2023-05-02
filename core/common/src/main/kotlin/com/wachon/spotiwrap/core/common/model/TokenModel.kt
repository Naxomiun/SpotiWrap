package com.wachon.spotiwrap.core.common.model

data class TokenModel(
    val accessToken: String,
    val expireTimestamp: Long,
    val refreshToken: String
)

data class RefreshTokenModel(
    val accessToken: String,
    val expireTimestamp: Long
)
