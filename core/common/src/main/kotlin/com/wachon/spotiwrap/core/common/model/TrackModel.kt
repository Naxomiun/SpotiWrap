package com.wachon.spotiwrap.core.common.model

data class TrackModel(
    val imageUrl: String,
    val title: String,
    val artists: List<ArtistModel>
) {

    fun getFormattedArtists(): String {
        return artists.joinToString(separator = ", ") { it.name }
    }

}