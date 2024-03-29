package com.wachon.spotiwrap.features.tracks.presentation.model

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TrackModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class TrackUI(
    val trackId: String,
    val trackFame: ItemFame,
    val trackTitle: String,
    val trackArtist: String,
    val trackAlbum: String,
    val trackImage: String,
    val trackDuration: String,
    val trackPopularity: Int,
    val trackExternalUrl: String,
)

fun TrackModel.toUI(): TrackUI {
    return TrackUI(
        trackId = id,
        trackFame = fame,
        trackTitle = title,
        trackImage = imageUrl,
        trackArtist = artists,
        trackAlbum = album,
        trackDuration = duration,
        trackPopularity = popularity,
        trackExternalUrl = externalUrl
    )
}

fun List<TrackModel>.toUI(): ImmutableList<TrackUI> {
    return this.map { it.toUI() }.toImmutableList()
}