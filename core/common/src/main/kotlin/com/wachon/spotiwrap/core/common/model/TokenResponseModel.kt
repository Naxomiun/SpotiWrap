package com.wachon.spotiwrap.core.common.model

data class TokenResponseModel(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val scope: String
)
