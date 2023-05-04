package com.wachon.spotiwrap.features.tracks.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class TrackUI(
    val trackTitle: String,
    val trackArtist: String,
    val trackImage: String
)