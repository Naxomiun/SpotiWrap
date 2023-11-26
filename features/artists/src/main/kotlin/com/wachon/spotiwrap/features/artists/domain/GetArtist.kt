package com.wachon.spotiwrap.features.artists.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.data.repository.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistUseCase {
    operator fun invoke(
        id: String
    ): Flow<ArtistModel>
}

class GetArtist(
    private val artistsRepository: ArtistsRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistUseCase {


    override fun invoke(id: String): Flow<ArtistModel> {
        return artistsRepository
            .getArtist(
                id = id
            )
            .flowOn(dispatchers.background)
    }

}