package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface SaveRefreshTokenUseCase {
    operator fun invoke(token: String)
}

class SaveRefreshToken(
    private val encryptedDataProvider: EncryptedDataProvider
) : SaveRefreshTokenUseCase {

    override fun invoke(token: String) {
        encryptedDataProvider.setEncryptedString(EncryptedItem.REFRESH_TOKEN, token)
    }

}