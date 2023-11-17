package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchedTrackApi(
    val tracks: TopApi,
)