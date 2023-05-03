package com.wachon.spotiwrap.features.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.auth.scopes.CheckScopesAreValidUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val checkScopesAreValidUseCase: CheckScopesAreValidUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun checkForPersistedLogin() {
        viewModelScope.launch {
            val areScopesValidated = withContext(dispatcherProvider.background) {
                checkScopesAreValidUseCase()
            }
            _state.update { it.copy(loading = false) }
            if(areScopesValidated) _event.send(Event.NavigateToHome)
            else _event.send(Event.NavigateToLogin)
        }
    }

    data class State(
        val loading: Boolean = true
    )

    sealed interface Event {
        object NavigateToLogin : Event
        object NavigateToHome : Event
    }

}