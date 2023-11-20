package com.wachon.spotiwrap.features.collage.domain

import com.wachon.spotiwrap.core.common.model.ArtistModel

interface GetArtistsCoversUseCase {
    operator fun invoke(
        artists: List<ArtistModel>
    ): List<String>
}

class GetArtistsCovers : GetArtistsCoversUseCase {

    override fun invoke(artists: List<ArtistModel>): List<String> =
        artists.map { it.imageUrl }.distinct()

}