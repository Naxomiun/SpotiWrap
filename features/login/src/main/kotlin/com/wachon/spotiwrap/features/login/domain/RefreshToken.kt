package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.core.auth.token.GetRefreshTokenUseCase
import com.wachon.spotiwrap.core.common.model.TokenResponseModel
import com.wachon.spotiwrap.features.login.data.TokenRepository

interface RefreshTokenUseCase {
    suspend operator fun invoke(): TokenResponseModel
}

class RefreshToken(
    private val getRefreshToken: GetRefreshTokenUseCase,
    private val tokenRepository: TokenRepository
) : RefreshTokenUseCase {

    override suspend fun invoke(): TokenResponseModel {
        return tokenRepository.getRefreshToken(getRefreshToken())
    }

}