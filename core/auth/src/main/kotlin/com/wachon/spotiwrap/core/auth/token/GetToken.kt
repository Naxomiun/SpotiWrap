package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.auth.TokenRepository
import com.wachon.spotiwrap.core.auth.model.TokenModel

interface GetTokenUseCase {
    suspend operator fun invoke(refresh: Boolean = false): TokenModel
}

class GetToken(
    private val tokenRepository: TokenRepository
) : GetTokenUseCase {

    override suspend fun invoke(refresh: Boolean): TokenModel {
        val token = tokenRepository.getPersistedToken()
        if(refresh) {
            return tokenRepository.refreshToken()
        }
        return token
    }

}