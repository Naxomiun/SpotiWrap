package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistTopTracksUseCase {
    operator fun invoke(
        id: String
    ): Flow<List<TrackModel>>
}

class GetArtistTopTracks(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistTopTracksUseCase {


    override fun invoke(id: String): Flow<List<TrackModel>> {
        return tracksRepository
            .getArtistTopTracks(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}