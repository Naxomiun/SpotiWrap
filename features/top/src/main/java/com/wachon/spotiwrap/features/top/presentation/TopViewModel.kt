package com.wachon.spotiwrap.features.top.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.design.ui.toItemUI
import com.wachon.spotiwrap.features.top.domain.GetArtistsUseCase
import com.wachon.spotiwrap.features.top.domain.GetTracksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopViewModel(
    private val getTracks: GetTracksUseCase,
    private val getArtists: GetArtistsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TopScreenState())
    val uiState: StateFlow<TopScreenState> = _uiState

    init {
        getArtistsList()
        getTracksList()
    }

    private fun getArtistsList() = viewModelScope.launch {
        getArtists(
            timeRange = _uiState.value.timeSelected
        ).collect { artists ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    content = artists.map { artist -> artist.toItemUI() }
                )
            }
        }
    }

    fun selectType(type: TopItemType) {
        _uiState.update { it.copy(typeSelected = type) }

        if (type == ARTISTS) {
            getArtistsList()
        } else {
            getTracksList()
        }
    }

    private fun getTracksList() = viewModelScope.launch {
        getTracks(
            timeRange = _uiState.value.timeSelected
        ).collect { tracks ->
            _uiState.update { it ->
                it.copy(
                    isLoading = false,
                    content = tracks.map { track -> track.toItemUI() }
                )
            }
        }
    }

    fun selectTime(time: TopItemTimeRange) {
        _uiState.update { it.copy(timeSelected = time) }
        selectType(_uiState.value.typeSelected)
    }

}