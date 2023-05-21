package com.wachon.spotiwrap.features.recommender.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.recommender.domain.GetGenresUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RecommenderViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getGenres: GetGenresUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecommenderScreenState())
    val uiState: StateFlow<RecommenderScreenState> = _uiState

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch(dispatcherProvider.background) {
            initGenres()
        }
    }

    private suspend fun initGenres() {
        getGenres().collect { genreList ->
            _uiState.value = _uiState.value.copy(genres = genreList)
        }
    }

    sealed interface Event
}