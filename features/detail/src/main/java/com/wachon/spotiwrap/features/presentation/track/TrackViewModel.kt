package com.wachon.spotiwrap.features.presentation.track

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.tracks.domain.GetTrackUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class TrackViewModel(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    getTrack: GetTrackUseCase
) : ViewModel() {

    private val trackId: String = checkNotNull(savedStateHandle["id"])
    private val track = getTrack(id = trackId)

    val uiState = combine(
        track
    ) { track ->
        TrackScreenState(
            loading = false,
            track = track.first().toUI()
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrackScreenState()
        )

}