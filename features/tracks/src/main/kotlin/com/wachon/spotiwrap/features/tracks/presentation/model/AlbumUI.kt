package com.wachon.spotiwrap.features.tracks.presentation.model

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class AlbumUI(
    val albumId: String,
    val albumName: String,
    val albumTotalTracks: Int,
    val albumDuration: String,
    val albumArtists: List<ArtistModel>,
    val albumArtistsNames: String,
    val albumImageUrl: String,
    val albumReleaseDate: String,
    val albumTracks: List<TrackModel>,
    val albumGenres: List<String>,
    val albumPopularity: Int,
    val albumExternalUrl: String,
    val albumLabel: String,
)


fun AlbumModel.toUI(): AlbumUI {
    return AlbumUI(
        albumId = id,
        albumName = name,
        albumTotalTracks = totalTracks,
        albumDuration = albumDuration,
        albumArtists = artists,
        albumArtistsNames = artists.joinToString(", ") { it.name },
        albumImageUrl = imageUrl,
        albumReleaseDate = releaseDate,
        albumTracks = tracks,
        albumGenres = genres,
        albumPopularity = popularity,
        albumExternalUrl = externalUrl,
        albumLabel = label,
    )
}

fun List<AlbumModel>.toUI(): ImmutableList<AlbumUI> {
    return this.map { it.toUI() }.toImmutableList()
}