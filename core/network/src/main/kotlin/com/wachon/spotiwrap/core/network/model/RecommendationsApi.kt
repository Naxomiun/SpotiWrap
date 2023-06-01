package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecommendationsApi(
    val tracks: List<TopItemApi>,
)
