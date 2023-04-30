package com.wachon.spotiwrap.features.login.data

data class TokenResponse(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int,
    val refreshToken: String,
    val scope: String
)