package com.wachon.spotiwrap.features.presentation.artist

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI

@Immutable
data class ArtistScreenState(
    val loading: Boolean = true,
    val artist: ArtistUI? = null
)