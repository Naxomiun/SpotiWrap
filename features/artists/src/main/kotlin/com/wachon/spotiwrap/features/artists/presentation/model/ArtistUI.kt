package com.wachon.spotiwrap.features.artists.presentation.model

import android.util.Log
import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class ArtistUI(
    val artistId: String,
    val artistName: String,
    val artistImage: String
)

fun ArtistModel.toUI(): ArtistUI {
    return ArtistUI(
        artistId = id,
        artistName = name,
        artistImage = imageUrl,
    )
}

fun List<ArtistModel>.toUI(): ImmutableList<ArtistUI> {
    return this.map { it.toUI() }.toImmutableList()
}