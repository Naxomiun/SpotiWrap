package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface SaveTokenUseCase {
    operator fun invoke(token: String)
}

class SaveToken(
    private val encryptedDataProvider: EncryptedDataProvider
) : SaveTokenUseCase {

    override fun invoke(token: String) {
        encryptedDataProvider.setEncryptedString(EncryptedItem.TOKEN, token)
    }

}