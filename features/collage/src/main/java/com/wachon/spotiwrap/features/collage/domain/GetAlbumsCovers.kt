package com.wachon.spotiwrap.features.collage.domain

import com.wachon.spotiwrap.core.common.model.TrackModel

interface GetAlbumsCoversUseCase {
    operator fun invoke(
        tracks: List<TrackModel>
    ): List<String>
}

class GetAlbumsCovers : GetAlbumsCoversUseCase {

    override fun invoke(tracks: List<TrackModel>): List<String> =
        tracks.map { it.imageUrl }.distinct()

}