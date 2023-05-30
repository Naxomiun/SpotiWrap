package com.wachon.spotiwrap.features.recommender.presentation

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import okhttp3.internal.immutableListOf

@Immutable
data class RecommenderScreenState(
    val genres: List<String> = immutableListOf(),

    val artistsQuery: String = "",
    val artistsSuggestions: List<ArtistModel> = mutableListOf(),
    val artistsSeeds: MutableList<ArtistModel> = mutableListOf(),

    val tracksQuery: String = "",
    val tracksSuggestions: List<TrackModel> = mutableListOf(),
    val tracksSeeds: MutableList<TrackModel> = mutableListOf(),
)