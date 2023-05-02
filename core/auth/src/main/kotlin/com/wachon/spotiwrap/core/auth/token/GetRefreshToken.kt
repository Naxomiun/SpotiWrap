package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedDataProvider
import com.wachon.spotiwrap.core.persistence.encrypted.EncryptedItem

interface GetRefreshTokenUseCase {
    operator fun invoke(): String
}

class GetRefreshToken(
    private val encryptedDataProvider: EncryptedDataProvider
) : GetRefreshTokenUseCase {

    override fun invoke(): String {
        return encryptedDataProvider.getEncryptedString(EncryptedItem.REFRESH_TOKEN) ?: ""
    }

}