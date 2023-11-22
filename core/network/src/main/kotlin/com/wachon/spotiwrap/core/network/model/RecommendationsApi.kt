package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsApi(
    @SerialName("tracks")
    val tracks: List<TopItemApi>,
)
