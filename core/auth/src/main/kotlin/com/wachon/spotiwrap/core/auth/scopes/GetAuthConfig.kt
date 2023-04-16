package com.wachon.spotiwrap.core.auth.scopes

interface GetAuthConfigUseCase {
    operator fun invoke(): AuthConfig
}

class GetAuthConfig(
    private val authConfigProvider: AuthConfigProvider
) : GetAuthConfigUseCase {

    override fun invoke(): AuthConfig {
        return authConfigProvider.getAuthConfig()
    }

}