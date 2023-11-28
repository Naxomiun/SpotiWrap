package com.wachon.spotiwrap.features.presentation.artist

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import okhttp3.internal.immutableListOf

@Immutable
data class ArtistScreenState(
    val loading: Boolean = true,
    val artist: ArtistUI? = null,
    val albums: List<AlbumModel> = immutableListOf(),
    val topTracks: List<TrackModel> = immutableListOf(),
    val related: List<ArtistModel> = immutableListOf(),
)