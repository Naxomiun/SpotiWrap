package com.wachon.spotiwrap.core.common.model

data class PlaylistModel(
    val id: String,
    val imageUrl: String,
    val name: String,
    val description: String,
    val owner: OwnerModel,
    val collaborative: Boolean,
    val public: Boolean,
    val tracks: List<TrackModel>,
    val type: String,
    val uri: String,
)

data class OwnerModel(
    val id: String,
    val type: String,
    val displayName: String,
)
