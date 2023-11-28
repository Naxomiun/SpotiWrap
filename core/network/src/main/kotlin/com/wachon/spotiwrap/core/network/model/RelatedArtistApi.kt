package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RelatedArtistApi(
    val artists: List<ArtistApi>?
)