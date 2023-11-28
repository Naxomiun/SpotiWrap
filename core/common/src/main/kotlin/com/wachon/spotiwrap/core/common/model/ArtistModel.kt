package com.wachon.spotiwrap.core.common.model

data class ArtistModel(
    val id: String,
    val fame: ItemFame,
    val name: String,
    var imageUrl: String,
    val genres: List<String>,
    val followers: Int = 0,
    val popularity: Int = 0,
    val externalUrl: String
)