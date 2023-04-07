package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.core.data.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.data.persistence.encrypted.EncryptedItem

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