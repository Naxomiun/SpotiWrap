package com.wachon.spotiwrap.features.collage.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.data.repository.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetArtistsUseCase {
    operator fun invoke(
        limit: Int = 50,
        offset: Int = 0,
        timeRange: TopItemTimeRange = TopItemTimeRange.MEDIUM_TERM
    ): Flow<List<ArtistModel>>
}

class GetArtists(
    private val artistsRepository: ArtistsRepository,
    private val dispatchers: DispatcherProvider
) : GetArtistsUseCase {

    override fun invoke(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>> {
        return artistsRepository
            .getTopArtistsFlow(
                limit = 50,
                timeRange = timeRange
            )
            .flowOn(dispatchers.background)
    }

}