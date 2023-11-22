package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecentlyItemApi(
    @SerialName("track") val track: TopItemApi,
    @SerialName("played_at") val playedAt: String
)