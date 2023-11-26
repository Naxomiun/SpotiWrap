package com.wachon.spotiwrap.features.presentation.track

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI

@Immutable
data class TrackScreenState(
    val loading: Boolean = true,
    val track: TrackUI? = null
)