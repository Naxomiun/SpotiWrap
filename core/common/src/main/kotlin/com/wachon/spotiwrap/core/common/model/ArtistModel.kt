package com.wachon.spotiwrap.core.common.model

data class ArtistModel(
    val id: String,
    val fame: ItemFame,
    val name: String,
    val imageUrl: String,
    val genres: List<String>,
)