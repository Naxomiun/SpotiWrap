package com.wachon.spotiwrap.features.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.wachon.spotiwrap.core.auth.scopes.AuthConfig
import com.wachon.spotiwrap.core.auth.scopes.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.auth.token.SaveTokenUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.login.domain.GetAccessTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getAuthConfig: GetAuthConfigUseCase,
    private val saveToken: SaveTokenUseCase,
    private val getAccessToken: GetAccessTokenUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    fun login() {
        viewModelScope.launch(dispatcherProvider.background) {
            val authConfig = getAuthConfig()
            _state.update {
                it.copy(
                    loading = true,
                    authConfig = authConfig
                )
            }
        }
    }

    fun handleLoginResponse(
        authorizationResponse: AuthorizationResponse,
        navigateToMenu: () -> Unit
    ) {
        viewModelScope.launch(dispatcherProvider.background) {
            when (authorizationResponse.type) {
                AuthorizationResponse.Type.TOKEN -> {
                    saveToken(authorizationResponse.accessToken)
                    withContext(dispatcherProvider.mainImmediate) {
                        navigateToMenu.invoke()
                    }
                }
                AuthorizationResponse.Type.CODE -> {
                    //TODO Save token response and check the expires time
                    var tokenResponse = getAccessToken(authorizationResponse.code)
                    Log.d("", "handleLoginResponse: ")
                }
                AuthorizationResponse.Type.ERROR -> {

                }
                AuthorizationResponse.Type.EMPTY -> {

                }
                AuthorizationResponse.Type.UNKNOWN -> {

                }
                else -> {

                }
            }
        }
    }

    data class State(
        val authConfig: AuthConfig? = null,
        val loading: Boolean = false
    )

}