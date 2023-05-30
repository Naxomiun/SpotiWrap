package com.wachon.spotiwrap.features.recommender.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.recommender.domain.GetGenresUseCase
import com.wachon.spotiwrap.features.recommender.domain.SearchArtistUseCase
import com.wachon.spotiwrap.features.recommender.domain.SearchTrackUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecommenderViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getGenres: GetGenresUseCase,
    private val searchArtistUseCase: SearchArtistUseCase,
    private val searchTrackUseCase: SearchTrackUseCase,
) : ViewModel() {

    private var searchArtistJob: Job? = null
    private var searchTrackJob: Job? = null

    //TODO Unify flows
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

    fun updateArtistQuery(query: String) {
        _uiState.update { it.copy(artistsQuery = query) }
        if (query.isNotEmpty()) {
            searchArtist(query)
        }
    }

    fun updateTrackQuery(query: String) {
        _uiState.update { it.copy(tracksQuery = query) }
        if (query.isNotEmpty()) {
            searchTrack(query)
        }
    }

    private fun searchArtist(query: String) {
        searchArtistJob?.cancel()
        searchArtistJob = viewModelScope.launch {
            searchArtistUseCase(query)
                .debounce(1000)
                .collect { artists ->
                    _uiState.value = _uiState.value.copy(artistsSuggestions = artists)
                }
        }
    }

    private fun searchTrack(query: String) {
        searchTrackJob?.cancel()
        searchTrackJob = viewModelScope.launch {
            searchTrackUseCase(query)
                .debounce(1000)
                .collect { tracks ->
                    _uiState.value = _uiState.value.copy(tracksSuggestions = tracks)
                }
        }
    }

    fun addArtistSeed(artist: ArtistModel) {
        _uiState.value = _uiState.value.copy(artistsSeeds = _uiState.value.artistsSeeds.toMutableList().apply {
            if (!this.contains(artist)) {
                add(artist)
            }
        })
    }

    fun addTrackSeed(track: TrackModel) {
        _uiState.value = _uiState.value.copy(tracksSeeds = _uiState.value.tracksSeeds.toMutableList().apply {
            if (!this.contains(track)) {
                add(track)
            }
        })
    }

    fun removeArtistSeed(artist: ArtistModel) {
        _uiState.value = _uiState.value.copy(artistsSeeds = _uiState.value.artistsSeeds.toMutableList().apply {
            remove(artist)
        })
    }

    fun removeTrackSeed(track: TrackModel) {
        _uiState.value = _uiState.value.copy(tracksSeeds = _uiState.value.tracksSeeds.toMutableList().apply {
            remove(track)
        })
    }

    fun clearArtistsSuggestions() {
        _uiState.value = _uiState.value.copy(artistsSuggestions = emptyList())
    }

    fun clearTracksSuggestions() {
        _uiState.value = _uiState.value.copy(tracksSuggestions = emptyList())
    }

    sealed interface Event
}