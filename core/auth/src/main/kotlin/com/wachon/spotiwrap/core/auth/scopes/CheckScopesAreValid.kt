package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.auth.config.AuthConfigProvider

interface CheckScopesAreValidUseCase {
    operator fun invoke(): Boolean
}

class CheckScopesAreValid(
    private val authConfigProvider: AuthConfigProvider,
    private val getAuthScopes: GetAuthScopesUseCase
) : CheckScopesAreValidUseCase {

    override fun invoke(): Boolean {
        val scopesFromAuthConfig: List<String>  = authConfigProvider.getScopes().map { it.name }
        val scopesSaved = getAuthScopes()?.toList() ?: return false
        return scopesSaved == scopesFromAuthConfig
    }

}