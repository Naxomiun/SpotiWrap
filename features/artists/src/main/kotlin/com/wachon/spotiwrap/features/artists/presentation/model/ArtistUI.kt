package com.wachon.spotiwrap.features.artists.presentation.model

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Immutable
data class ArtistUI(
    val artistId: String,
    val artistFame: ItemFame,
    val artistName: String,
    val artistImage: String,
    val artistGenres: List<String> = emptyList(),
)

fun ArtistModel.toUI(): ArtistUI {
    return ArtistUI(
        artistId = id,
        artistFame = fame,
        artistName = name,
        artistImage = imageUrl,
        artistGenres = genres
    )
}

fun List<ArtistModel>.toUI(): ImmutableList<ArtistUI> {
    return this.map { it.toUI() }.toImmutableList()
}