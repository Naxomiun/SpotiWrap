package com.wachon.spotiwrap.features.menu.presentation.categories.artist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArtistViewModel : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    data class State(
        val loading: Boolean = false
    )
}