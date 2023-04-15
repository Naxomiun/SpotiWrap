package com.wachon.spotiwrap.features.menu.presentation.categories.track

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import com.wachon.spotiwrap.features.menu.presentation.MenuCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TrackViewModel(
    private val getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    init {
        getTopTracks()
    }

    private fun getTopTracks() {
        viewModelScope.launch(Dispatchers.IO) {
            val top = getUserTopItemsUseCase(MenuCategory.TRACKS, 5, 0, "medium_term")
            _state.update {
                it.copy(
                    top = top
                )
            }
        }
    }

    data class State(
        val loading: Boolean = false,
        val top: Top? = null
    )

}