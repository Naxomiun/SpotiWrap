package com.wachon.spotiwrap.core.auth

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface GetTokenUseCase {
    operator fun invoke(): String?
}

class GetToken(
    private val encryptedDataProvider: EncryptedDataProvider
) : GetTokenUseCase {

    override fun invoke(): String? {
        return encryptedDataProvider.getEncryptedString(EncryptedItem.TOKEN.name.lowercase())
    }

}