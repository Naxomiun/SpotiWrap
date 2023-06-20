package com.wachon.spotiwrap.core.common.model

data class UserProfileModel(
    val displayName: String,
    val country: String,
    val email: String,
    val image: String,
    val followers: Int,
    val currentSong: CurrentTrackModel?
)

data class CurrentTrackModel(
    val title: String,
    val artist: String,
    val imageUrl: String
)
