package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.auth.config.AuthConfigProvider

interface CheckScopesAreValidUseCase {
    operator fun invoke(): Boolean
}

class CheckScopesAreValid(
    private val authConfigProvider: AuthConfigProvider,
    private val getAuthScopes: GetAuthScopesUseCase,
    private val saveAuthScopes: SaveAuthScopesUseCase
) : CheckScopesAreValidUseCase {

    override fun invoke(): Boolean {
        val scopesFromAuthConfig: List<String> = authConfigProvider.getScopes().map { it.name }
        val scopesSaved: List<String> = getAuthScopes() ?: listOf()

        return if (scopesSaved == scopesFromAuthConfig) {
            true
        } else {
            saveAuthScopes(scopesFromAuthConfig)
            false
        }
    }

}