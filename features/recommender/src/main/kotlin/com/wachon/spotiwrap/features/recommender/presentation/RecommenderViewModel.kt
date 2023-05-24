package com.wachon.spotiwrap.features.recommender.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.features.recommender.domain.GetGenresUseCase
import com.wachon.spotiwrap.features.recommender.domain.SearchArtistUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RecommenderViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getGenres: GetGenresUseCase,
    private val searchItemUseCase: SearchArtistUseCase
) : ViewModel() {

    private var searchJob: Job? = null

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

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(artistsQuery = query)
        if (query.isNotEmpty()) {
            searchItem(query)
        }
    }

    private fun searchItem(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            searchItemUseCase(query)
                .debounce(1000)
                .collect { artists ->
                    _uiState.value = _uiState.value.copy(artistsSuggestions = artists)
                }
        }
    }

    fun addSeed(artist: ArtistModel) {
        _uiState.value = _uiState.value.copy(artistsSeeds = _uiState.value.artistsSeeds.toMutableList().apply {
            if (!isAlreadyAdded(artist)) {
                add(artist)
            }
        })
    }

    private fun isAlreadyAdded(artist: ArtistModel): Boolean {
        return _uiState.value.artistsSeeds.contains(artist)
    }

    fun removeSeed(artist: ArtistModel) {
        _uiState.value = _uiState.value.copy(artistsSeeds = _uiState.value.artistsSeeds.toMutableList().apply {
            remove(artist)
        })
    }

    fun clearSuggestions() {
        _uiState.value = _uiState.value.copy(artistsSuggestions = emptyList())
    }

    sealed interface Event
}