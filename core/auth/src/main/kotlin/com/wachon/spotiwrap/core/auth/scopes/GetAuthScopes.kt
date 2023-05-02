package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface GetAuthScopesUseCase {
    operator fun invoke(): List<String>?
}

class GetAuthScopes(
    private val encryptedDataProvider: EncryptedDataProvider
) : GetAuthScopesUseCase {

    override fun invoke(): List<String>? {
        return encryptedDataProvider.getCustomObject(EncryptedItem.SCOPES)
    }

}