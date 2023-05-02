package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.core.auth.scopes.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.common.model.TokenResponseModel
import com.wachon.spotiwrap.features.login.data.TokenRepository

interface GetAccessTokenUseCase {
    suspend operator fun invoke(code: String): TokenResponseModel
}

class GetAccessToken(
    private val getAuthConfig: GetAuthConfigUseCase,
    private val tokenRepository: TokenRepository
) : GetAccessTokenUseCase {

    override suspend fun invoke(code: String): TokenResponseModel {
        return tokenRepository.getAccessToken(code, getAuthConfig())
    }

}