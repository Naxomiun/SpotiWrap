package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistsFromTrackUseCase {
    operator fun invoke(
        id: String
    ): Flow<List<ArtistModel>>
}

class GetArtistsFromTrack(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistsFromTrackUseCase {


    override fun invoke(id: String): Flow<List<ArtistModel>> {
        return tracksRepository
            .getArtistFromTrack(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}