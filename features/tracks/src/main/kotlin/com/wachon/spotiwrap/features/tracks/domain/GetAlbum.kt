package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.data.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetAlbumUseCase {
    operator fun invoke(
        id: String
    ): Flow<AlbumModel>
}

class GetAlbum(
    private val albumRepository: AlbumRepository,
    private val dispatchers: DispatcherProvider
) : GetAlbumUseCase {


    override fun invoke(id: String): Flow<AlbumModel> {
        return albumRepository
            .getAlbum(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}