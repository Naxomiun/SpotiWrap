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
    val artistFollowers: Int,
    val artistPopularity: Int,
    val artistExternalUrl: String,
)

fun ArtistModel.toUI(): ArtistUI {
    return ArtistUI(
        artistId = id,
        artistFame = fame,
        artistName = name,
        artistImage = imageUrl,
        artistGenres = genres,
        artistFollowers = followers,
        artistPopularity = popularity,
        artistExternalUrl = externalUrl
    )
}

fun List<ArtistModel>.toUI(): ImmutableList<ArtistUI> {
    return this.map { it.toUI() }.toImmutableList()
}