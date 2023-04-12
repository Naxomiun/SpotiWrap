package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.core.data.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.data.persistence.encrypted.EncryptedItem

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