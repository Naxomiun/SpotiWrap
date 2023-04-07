package com.wachon.spotiwrap.features.login.data

data class AuthConfig (
    val clientId: String,
    val scopes: List<String>,
    val campaign: String,
    val redirectUrl: String
)