package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class TrackFeaturesApi(
    val acousticness: Float,
    val danceability: Float,
    val energy: Float,
    val instrumentalness: Float,
    val liveness: Float,
    val speechiness: Float,
)