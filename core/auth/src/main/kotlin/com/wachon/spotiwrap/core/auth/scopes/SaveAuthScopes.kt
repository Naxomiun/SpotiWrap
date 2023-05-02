package com.wachon.spotiwrap.core.auth.scopes

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface SaveAuthScopesUseCase {
    operator fun invoke(scopes: List<String>)
}

class SaveAuthScopes(
    private val encryptedDataProvider: EncryptedDataProvider
) : SaveAuthScopesUseCase {

    override fun invoke(scopes: List<String>) {
        encryptedDataProvider.setCustomObject(EncryptedItem.SCOPES, scopes)
    }

}