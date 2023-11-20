package com.wachon.spotiwrap.features.collage.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetAlbumsUseCase {
    operator fun invoke(
        limit: Int = 50,
        timeRange: TopItemTimeRange = TopItemTimeRange.MEDIUM_TERM
    ): Flow<List<TrackModel>>
}

class GetAlbums(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetAlbumsUseCase {

    override fun invoke(
        limit: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> {
        return tracksRepository
            .getTopTracksFlow(
                limit = 50,
                timeRange = timeRange
            )
            .flowOn(dispatchers.background)
    }

}