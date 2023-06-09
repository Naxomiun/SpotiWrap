package com.wachon.spotiwrap.core.common.model

data class TrackModel(
    val id: String,
    val fame: ItemFame,
    val imageUrl: String,
    val title: String,
    val artists: String
)