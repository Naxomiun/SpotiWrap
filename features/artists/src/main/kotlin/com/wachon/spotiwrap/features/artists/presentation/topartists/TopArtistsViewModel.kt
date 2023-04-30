package com.wachon.spotiwrap.features.artists.presentation.topartists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TopArtistsViewModel(
    getTopArtists: GetUserTopArtistsUseCase
) : ViewModel() {

    val uiState = getTopArtists()
        .map { TopArtistsScreenState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily, //TODO: CHANGE BY WhileSubscribedOrRetained
            initialValue = TopArtistsScreenState.Loading
        )

}
