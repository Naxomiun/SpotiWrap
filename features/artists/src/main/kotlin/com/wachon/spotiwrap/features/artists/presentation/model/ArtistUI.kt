package com.wachon.spotiwrap.features.artists.presentation.model

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class ArtistUI(
    val artistId: String,
    val artistName: String,
    val artistImage: String,
    val artistGenres: List<String> = emptyList(),
)

fun ArtistModel.toUI(): ArtistUI {
    return ArtistUI(
        artistId = id,
        artistName = name,
        artistImage = imageUrl,
        artistGenres = genres
    )
}

fun List<ArtistModel>.toUI(): ImmutableList<ArtistUI> {
    return this.map { it.toUI() }.toImmutableList()
}