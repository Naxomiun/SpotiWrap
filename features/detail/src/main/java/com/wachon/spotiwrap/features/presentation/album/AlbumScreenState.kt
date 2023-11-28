package com.wachon.spotiwrap.features.presentation.album

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.tracks.presentation.model.AlbumUI
import okhttp3.internal.immutableListOf

@Immutable
data class AlbumScreenState(
    val loading: Boolean = true,
    val album: AlbumUI? = null,
    val tracks: List<TrackModel> = immutableListOf()
)