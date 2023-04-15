package com.wachon.spotiwrap.features.menu.presentation.categories.track

import androidx.lifecycle.ViewModel
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.domain.GetTokenUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TrackViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    data class State(
        val loading: Boolean = false,
        val top: Top? = null
    )

}