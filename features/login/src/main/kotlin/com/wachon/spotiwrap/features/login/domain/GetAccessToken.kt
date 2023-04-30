package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.login.data.TokenResponse
import com.wachon.spotiwrap.features.login.data.TokenService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetAccessTokenUseCase {
    operator fun invoke(): Flow<TokenResponse>
}

class GetAccessToken(
    private val tokenService: TokenService,
    private val dispatchers: DispatcherProvider
) : GetAccessTokenUseCase {

    override fun invoke(): Flow<TokenResponse> {
        return tokenService
            .getAccessToken()
            .flowOn(dispatchers.background)
    }

}