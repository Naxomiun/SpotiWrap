package com.wachon.spotiwrap.features.presentation.track

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.TrackFeaturesModel
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import com.wachon.spotiwrap.features.tracks.presentation.model.AlbumUI
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI
import okhttp3.internal.immutableListOf

@Immutable
data class TrackScreenState(
    val loading: Boolean = true,
    val track: TrackUI? = null,
    val features: TrackFeaturesModel? = null,
    val album: AlbumUI? = null,
    val artists: List<ArtistUI> = immutableListOf(),
)