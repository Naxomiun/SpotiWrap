package com.wachon.spotiwrap.core.common.model

data class TrackModel(
    val id: String,
    val imageUrl: String,
    val title: String,
    val artists: List<ArtistModel>
)