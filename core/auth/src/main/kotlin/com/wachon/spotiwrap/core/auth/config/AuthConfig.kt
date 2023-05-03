package com.wachon.spotiwrap.core.auth.config

import com.wachon.spotiwrap.core.auth.scopes.AuthScope


data class AuthConfig (
    val clientId: String,
    val clientBase64: String,
    val scopes: List<AuthScope>,
    val campaign: String,
    val redirectUrl: String
) {

    fun getScopesAsTypedArray(): Array<String> {
        return scopes.map {
            it.value
        }.toTypedArray()
    }

}