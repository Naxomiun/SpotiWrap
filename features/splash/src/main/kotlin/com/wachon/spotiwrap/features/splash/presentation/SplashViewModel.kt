package com.wachon.spotiwrap.features.splash.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.auth.scopes.CheckScopesAreValidUseCase
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel(
    private val dispatcherProvider: DispatcherProvider,
    private val checkScopesAreValidUseCase: CheckScopesAreValidUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    init {
        viewModelScope.launch(dispatcherProvider.background) {
            val areScopesValidated = checkScopesAreValidUseCase()
            _state.update {
                it.copy(
                    loading = false,
                    scopesChecked = areScopesValidated
                )
            }
        }
    }

    data class State(
        val loading: Boolean = true,
        val scopesChecked: Boolean? = null
    )
}