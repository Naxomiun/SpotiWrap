package com.wachon.spotiwrap.features.login.domain

import com.wachon.spotiwrap.core.auth.scopes.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.login.data.TokenResponse
import com.wachon.spotiwrap.features.login.data.TokenService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetAccessTokenUseCase {
    operator fun invoke(code: String): Flow<TokenResponse>
}

class GetAccessToken(
    private val getAuthConfig: GetAuthConfigUseCase,
    private val tokenService: TokenService,
    private val dispatchers: DispatcherProvider
) : GetAccessTokenUseCase {

    override fun invoke(code: String): Flow<TokenResponse> {
        return tokenService
            .getAccessToken(code, getAuthConfig())
            .flowOn(dispatchers.background)
    }

}