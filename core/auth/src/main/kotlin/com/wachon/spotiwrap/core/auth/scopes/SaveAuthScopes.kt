package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.auth.config.AuthConfigProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.DataProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.SharedPreferencesItem

interface SaveAuthScopesUseCase {
    operator fun invoke()
}

class SaveAuthScopes(
    private val authProvider: AuthConfigProvider,
    private val dataProvider: DataProvider
) : SaveAuthScopesUseCase {

    override fun invoke() {
        dataProvider.setObject(SharedPreferencesItem.AUTH_SCOPES, authProvider.getScopes())
    }

}