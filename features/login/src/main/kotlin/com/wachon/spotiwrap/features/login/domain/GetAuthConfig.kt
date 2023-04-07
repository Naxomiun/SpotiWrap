package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.features.login.data.AuthConfig

interface GetAuthConfigUseCase {
    operator fun invoke(): AuthConfig
}

class GetAuthConfig(
    private val authConfig: AuthConfig
) : GetAuthConfigUseCase {

    override fun invoke(): AuthConfig {
        return authConfig
    }

}