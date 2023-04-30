package com.wachon.spotiwrap.features.tracks.presentation.toptracks

import com.wachon.spotiwrap.core.common.model.TrackModel

sealed interface TopTracksScreenState {
    object Loading : TopTracksScreenState
    data class Success(val tracks: List<TrackModel>) : TopTracksScreenState
}