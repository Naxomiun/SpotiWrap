package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.auth.TokenRepository
import com.wachon.spotiwrap.core.common.model.TokenModel

interface GetTokenUseCase {
    suspend operator fun invoke(): TokenModel
}

class GetToken(
    private val tokenRepository: TokenRepository
) : GetTokenUseCase {

    override suspend fun invoke(): TokenModel {
        val token = tokenRepository.getPersistedToken()
        if(!tokenHasExpired(token.expireTimestamp)) {
            return token
        }
        return tokenRepository.refreshToken()
    }

    private fun tokenHasExpired(expireTimestamp: Long): Boolean {
        return expireTimestamp < System.currentTimeMillis()
    }

}