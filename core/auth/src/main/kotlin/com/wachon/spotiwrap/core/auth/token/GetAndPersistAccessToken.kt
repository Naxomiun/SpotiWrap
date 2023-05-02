package com.wachon.spotiwrap.core.auth.token

import com.wachon.spotiwrap.core.auth.TokenRepository
import com.wachon.spotiwrap.core.auth.config.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.common.model.TokenModel

interface GetAndPersistAccessTokenUseCase {
    suspend operator fun invoke(code: String): TokenModel
}

class GetAndPersistAccessToken(
    private val getAuthConfig: GetAuthConfigUseCase,
    private val tokenRepository: TokenRepository
) : GetAndPersistAccessTokenUseCase {

    override suspend fun invoke(code: String): TokenModel {
        return tokenRepository.getAccessToken(code, getAuthConfig())
    }

}