package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetAlbumTracksUseCase {
    operator fun invoke(
        id: String
    ): Flow<List<TrackModel>>
}

class GetAlbumTracks(
    private val albumRepository: AlbumRepository,
    private val dispatchers: DispatcherProvider
) : GetAlbumTracksUseCase {


    override fun invoke(id: String): Flow<List<TrackModel>> {
        return albumRepository
            .getAlbumTracks(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}