package com.wachon.spotiwrap.features.top.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.LONG_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.MEDIUM_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTIST
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACK
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS
import com.wachon.spotiwrap.core.design.ui.TopItemUI
import com.wachon.spotiwrap.core.design.ui.toItemUI
import com.wachon.spotiwrap.features.top.domain.GetArtistsUseCase
import com.wachon.spotiwrap.features.top.domain.GetTracksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TopViewModel(
    getTracks: GetTracksUseCase,
    getArtists: GetArtistsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TopScreenState())
    val uiState: StateFlow<TopScreenState> = _uiState

    private val tracksShort = getTracks(timeRange = SHORT_TERM)
    private val tracksMedium = getTracks(timeRange = MEDIUM_TERM)
    private val tracksLong = getTracks(timeRange = LONG_TERM)
    private val artistsShort = getArtists(timeRange = SHORT_TERM)
    private val artistsMedium = getArtists(timeRange = MEDIUM_TERM)
    private val artistsLong = getArtists(timeRange = LONG_TERM)


    private val tracks = combine(
        tracksShort,
        tracksMedium,
        tracksLong,
    ) { tracksShort, tracksMedium, tracksLong ->
        _uiState.update {
            it.copy(
                isLoading = false,
                content = tracksShort.map { it.toItemUI() },
                tracksShort = tracksShort.map { it.toItemUI() },
                tracksMedium = tracksMedium.map { it.toItemUI() },
                tracksLong = tracksLong.map { it.toItemUI() },
            )
        }
    }

    private val artists = combine(
        artistsShort,
        artistsMedium,
        artistsLong,
    )
    { artistsShort, artistsMedium, artistsLong ->
        _uiState.update {
            it.copy(
                artistsShort = artistsShort.map { it.toItemUI() },
                artistsMedium = artistsMedium.map { it.toItemUI() },
                artistsLong = artistsLong.map { it.toItemUI() },
            )
        }
    }

    init {
        viewModelScope.launch {
            tracks.collect {}
            artists.collect {}
        }
    }


    fun selectType(type: TopItemType) {
        _uiState.update {
            it.copy(
                typeSelected = type,
                content = getContent(type = type, time = _uiState.value.timeSelected)
            )
        }
    }

    fun selectTime(time: TopItemTimeRange) {
        _uiState.update {
            it.copy(
                timeSelected = time,
                content = getContent(type = _uiState.value.typeSelected, time = time)
            )
        }
    }

    private fun getContent(type: TopItemType, time: TopItemTimeRange): List<TopItemUI> =
        with(_uiState.value) {
            when (type) {
                TRACK,
                TRACKS -> when (time) {
                    SHORT_TERM -> tracksShort
                    MEDIUM_TERM -> tracksMedium
                    LONG_TERM -> tracksLong
                }

                ARTIST,
                ARTISTS -> when (time) {
                    SHORT_TERM -> artistsShort
                    MEDIUM_TERM -> artistsMedium
                    LONG_TERM -> artistsLong
                }
            }
        }

}