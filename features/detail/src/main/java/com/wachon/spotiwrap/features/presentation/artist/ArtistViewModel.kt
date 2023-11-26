package com.wachon.spotiwrap.features.presentation.artist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetArtistUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn

class ArtistViewModel(
    savedStateHandle: SavedStateHandle,
    dispatcherProvider: DispatcherProvider,
    getArtist: GetArtistUseCase
) : ViewModel() {

    private val artistId: String = checkNotNull(savedStateHandle["id"])
    private val artist = getArtist(id = artistId)

    val uiState = combine(
        artist
    ) { artist ->
        ArtistScreenState(
            loading = false,
            artist = artist.first().toUI()
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ArtistScreenState()
        )
}