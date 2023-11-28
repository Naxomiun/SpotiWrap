package com.wachon.spotiwrap.core.common.model

data class AlbumModel(
    val id: String,
    val name: String,
    val totalTracks: Int,
    val artists: List<ArtistModel>,
    val imageUrl: String,
    val releaseDate: String,
    val releaseDatePrecision: String,
    val tracks: List<TrackModel>,
    val genres: List<String>,
    val popularity: Int,
    val label: String,
    val externalUrl: String,
    val albumDuration: String,
)
