package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.persistence.sharedpreferences.DataProvider
import com.wachon.spotiwrap.core.persistence.sharedpreferences.SharedPreferencesItem

interface GetAuthScopesUseCase {
    operator fun invoke(): List<AuthScope>?
}

class GetAuthScopes(
    private val dataProvider: DataProvider
) : GetAuthScopesUseCase {

    override fun invoke(): List<AuthScope>? {
        return dataProvider.getObject<List<AuthScope>>(SharedPreferencesItem.AUTH_SCOPES)
    }

}