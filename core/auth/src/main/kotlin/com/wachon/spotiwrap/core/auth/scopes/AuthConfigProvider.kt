package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.auth.BuildConfig

interface AuthConfigProvider {
    fun getScopes(): List<AuthScopes>
    fun getClientId(): String
    fun getCampaignId(): String
    fun getClientBase64(): String
    fun getRedirectUrl(): String
    fun getAuthConfig(): AuthConfig
}

class DefaultAuthConfigProvider : AuthConfigProvider {

    private val clientId = BuildConfig.CLIENT_ID

    private val campaignId = BuildConfig.CAMPAIGN_ID

    private val clientBase64 = BuildConfig.CLIENT_BASE_64

    private val redirectUrl = "spotiwrap://auth"

    private val scopes = listOf(
        AuthScopes.UserReadEmail,
        AuthScopes.UserReadPrivate,
        AuthScopes.UserTopRead,
        AuthScopes.Streaming
    )

    override fun getScopes(): List<AuthScopes> {
        return scopes
    }

    override fun getClientId(): String {
        return clientId
    }

    override fun getCampaignId(): String {
        return campaignId
    }

    override fun getClientBase64(): String {
        return clientBase64
    }

    override fun getRedirectUrl(): String {
        return redirectUrl
    }

    override fun getAuthConfig(): AuthConfig {
        return AuthConfig(
            clientId = clientId,
            clientBase64 = clientBase64,
            campaign = campaignId,
            scopes = scopes,
            redirectUrl = redirectUrl
        )
    }

}