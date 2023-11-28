package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistRelatedUseCase {
    operator fun invoke(
        id: String
    ): Flow<List<ArtistModel>>
}

class GetArtistRelated(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistRelatedUseCase {


    override fun invoke(id: String): Flow<List<ArtistModel>> {
        return tracksRepository
            .getArtistRelated(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}