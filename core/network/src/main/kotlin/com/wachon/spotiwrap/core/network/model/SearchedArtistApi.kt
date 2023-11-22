package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchedArtistApi(
    val artists: TopApi
)