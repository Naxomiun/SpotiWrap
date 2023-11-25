package com.wachon.spotiwrap.features.top.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetTracksUseCase {
    operator fun invoke(
        limit: Int = 50,
        timeRange: TopItemTimeRange = TopItemTimeRange.MEDIUM_TERM
    ): Flow<List<TrackModel>>
}

class GetTracks(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetTracksUseCase {

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