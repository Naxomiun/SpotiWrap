package com.wachon.spotiwrap.core.common.model

data class TrackModel(
    val id: String,
    val fame: ItemFame,
    var imageUrl: String,
    val title: String,
    val artists: String,
    val artistsIds: List<String>,
    val album: String,
    val albumId: String,
    val playedAt: String = "",
    val uri: String,
    val duration: String,
    val popularity: Int,
    val externalUrl: String,
)