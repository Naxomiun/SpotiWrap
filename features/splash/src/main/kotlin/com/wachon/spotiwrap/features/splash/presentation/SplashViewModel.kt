package com.wachon.spotiwrap.features.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.auth.scopes.CheckScopesAreValidUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val checkScopesAreValidUseCase: CheckScopesAreValidUseCase
) : ViewModel() {

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    fun checkForPersistedLogin() {
        viewModelScope.launch(dispatcherProvider.background) {
            val areScopesValidated = checkScopesAreValidUseCase()
            if(areScopesValidated) _event.send(Event.NavigateToHome)
            else _event.send(Event.NavigateToLogin)
        }
    }

    sealed interface Event {
        object NavigateToLogin : Event
        object NavigateToHome : Event
    }

}