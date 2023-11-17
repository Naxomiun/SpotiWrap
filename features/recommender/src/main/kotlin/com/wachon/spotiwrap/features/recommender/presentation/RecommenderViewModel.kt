package com.wachon.spotiwrap.features.recommender.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.recommender.domain.AddTracksToPlaylistUseCase
import com.wachon.spotiwrap.features.recommender.domain.GetPlaylistItemsUseCase
import com.wachon.spotiwrap.features.recommender.domain.GetRecommendationsUseCase
import com.wachon.spotiwrap.features.recommender.domain.GetUserPlaylistsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecommenderViewModel(
    dispatcherProvider: DispatcherProvider,
    private val getUserPlaylists: GetUserPlaylistsUseCase,
    private val getPlaylistItems: GetPlaylistItemsUseCase,
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val addTracksToPlaylistUseCase: AddTracksToPlaylistUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecommenderScreenState())
    val uiState: StateFlow<RecommenderScreenState> = _uiState

    private var searchRecommendationJob: Job? = null

    init {
        viewModelScope.launch(dispatcherProvider.background) {
            initUserPlaylists()
        }
    }

    private fun initUserPlaylists() =
        viewModelScope.launch {
            getUserPlaylists().collect { playlists ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    playlists = playlists
                )
            }
        }

    fun refreshPlaylists() = initUserPlaylists()

    fun updatePlaylistAndSongs(hasToSave: Boolean, playlist: PlaylistModel) =
        if (hasToSave) {
            selectPlaylist(playlist)
            searchRecommendedSongs(playlist = playlist)
        } else {
            removePlaylist()
            removeRecommendations()
        }

    private fun selectPlaylist(playlist: PlaylistModel) =
        _uiState.update { it.copy(playlistSelected = playlist) }

    private fun searchRecommendedSongs(deletePrevious: Boolean = true, playlist: PlaylistModel) {
        _uiState.update { it.copy(isLoadingRecommendations = true) }
        searchRecommendationJob = viewModelScope.launch {
            getPlaylistItems(id = playlist.id).collect { trackList ->
                getRecommendationsUseCase(
                    tracks = trackList.shuffled().take(5).joinToString(",") { it.id }
                ).collect { recommendations ->
                    _uiState.update {
                        it.copy(
                            isLoadingRecommendations = false,
                            recommendations =
                            if (deletePrevious) {
                                recommendations.toMutableList()
                            } else {
                                (it.recommendations + recommendations).toMutableList()
                            }
                        )
                    }
                }
            }

        }
    }

    private fun removePlaylist() =
        _uiState.update { it.copy(playlistSelected = null) }

    private fun removeRecommendations() {
        searchRecommendationJob?.cancel()
        _uiState.update {
            it.copy(
                isLoadingRecommendations = false,
                recommendations = mutableListOf()
            )
        }
    }

    fun addTrackToCurrentPlaylist(track: TrackModel) =
        viewModelScope.launch {
            addTracksToPlaylistUseCase(
                playlistId = _uiState.value.playlistSelected?.id ?: "",
                tracksUri = mutableListOf(track.uri)
            ).collect {
                _uiState.update {
                    it.copy(recommendations = it.recommendations.toMutableList().apply {
                        remove(track)
                    })
                }
                hasToFillRecommendations()
            }
        }

    private fun hasToFillRecommendations() {
        if (_uiState.value.recommendations.size == 10) {
            refreshRecommendations(deletePrevious = false)
        }
    }

    fun refreshRecommendations(deletePrevious: Boolean = true) =
        _uiState.value.playlistSelected?.let {
            searchRecommendedSongs(
                deletePrevious = deletePrevious,
                playlist = it
            )
        }
}