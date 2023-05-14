package com.wachon.spotiwrap.features.artists.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.data.ArtistsRepository
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists.Companion.DEFAULT_LIMIT
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists.Companion.DEFAULT_OFFSET
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtists.Companion.DEFAULT_TIME_RANGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetUserTopArtistsUseCase {
    operator fun invoke(
        limit: Int = DEFAULT_LIMIT,
        offset: Int = DEFAULT_OFFSET,
        timeRange: TopItemTimeRange = DEFAULT_TIME_RANGE
    ): Flow<List<ArtistModel>>
}

class GetUserTopArtists(
    private val artistsRepository: ArtistsRepository,
    private val dispatchers: DispatcherProvider
) : GetUserTopArtistsUseCase {

    companion object {
        const val DEFAULT_LIMIT = 10
        const val DEFAULT_OFFSET = 0
        val DEFAULT_TIME_RANGE = TopItemTimeRange.MEDIUM_TERM
    }

    override fun invoke(limit: Int, offset: Int, timeRange: TopItemTimeRange): Flow<List<ArtistModel>> {
        return artistsRepository
            .getTopArtists(
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
            .flowOn(dispatchers.background)
    }

}