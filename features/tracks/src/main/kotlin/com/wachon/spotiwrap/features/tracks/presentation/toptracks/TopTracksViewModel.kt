package com.wachon.spotiwrap.features.tracks.presentation.toptracks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TopTracksViewModel(
    getTopTracks: GetUserTopTracksUseCase
) : ViewModel() {

    val uiState = getTopTracks()
        .map { TopTracksScreenState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, //TODO: CHANGE BY WhileSubscribedOrRetained
            initialValue = TopTracksScreenState.Loading
        )

}