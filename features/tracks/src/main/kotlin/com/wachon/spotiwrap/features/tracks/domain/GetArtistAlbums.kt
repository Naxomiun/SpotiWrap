package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistAlbumsUseCase {
    operator fun invoke(
        id: String
    ): Flow<List<AlbumModel>>
}

class GetArtistAlbums(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistAlbumsUseCase {


    override fun invoke(id: String): Flow<List<AlbumModel>> {
        return tracksRepository
            .getArtistAlbums(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}