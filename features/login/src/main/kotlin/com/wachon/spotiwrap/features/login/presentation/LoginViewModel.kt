package com.wachon.spotiwrap.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.wachon.spotiwrap.core.auth.config.AuthConfig
import com.wachon.spotiwrap.core.auth.config.GetAuthConfigUseCase
import com.wachon.spotiwrap.core.auth.scopes.SaveAuthScopesUseCase
import com.wachon.spotiwrap.core.auth.token.GetAndPersistAccessTokenUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val getAuthConfig: GetAuthConfigUseCase,
    private val getAccessToken: GetAndPersistAccessTokenUseCase,
    private val saveAuthScopes: SaveAuthScopesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> get() = _state

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun login() {
        viewModelScope.launch(dispatcherProvider.background) {
            val authConfig = getAuthConfig()
            _state.update { it.copy(loading = true) }
            _event.send(Event.AuthConfigReceived(authConfig))
        }
    }

    fun handleLoginResponse(
        authorizationResponse: AuthorizationResponse
    ) {
        viewModelScope.launch(dispatcherProvider.background) {
            when (authorizationResponse.type) {
                AuthorizationResponse.Type.TOKEN -> {

                }
                AuthorizationResponse.Type.CODE -> {
                    getAccessToken(authorizationResponse.code)
                    saveAuthScopes()
                    _event.send(Event.NavigateToHome)
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
        val loading: Boolean = true
    )

    sealed interface Event {
        data class AuthConfigReceived(val authConfig: AuthConfig) : Event
        object NavigateToHome : Event
    }



}