package com.wachon.spotiwrap.presentation.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    val state: StateFlow<LoginState>
        get() = _state
    private val _state = MutableStateFlow(LoginState())


    data class LoginState(
        val isLoading: Boolean = false
    )
}