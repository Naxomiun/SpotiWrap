package com.wachon.spotiwrap.core.auth.config

import com.wachon.spotiwrap.core.auth.scopes.AuthScopes


data class AuthConfig (
    val clientId: String,
    val clientBase64: String,
    val scopes: List<AuthScopes>,
    val campaign: String,
    val redirectUrl: String
) {

    fun getScopesAsTypedArray(): Array<String> {
        return scopes.map {
            it.value
        }.toTypedArray()
    }

}