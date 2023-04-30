package com.wachon.spotiwrap.features.artists.presentation.topartists

import com.wachon.spotiwrap.core.common.model.ArtistModel

sealed interface TopArtistsScreenState {
    object Loading : TopArtistsScreenState
    data class Success(val artists: List<ArtistModel>) : TopArtistsScreenState
}