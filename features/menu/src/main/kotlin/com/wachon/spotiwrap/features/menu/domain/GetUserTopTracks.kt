package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracks.Companion.DEFAULT_LIMIT
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracks.Companion.DEFAULT_OFFSET
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracks.Companion.DEFAULT_TIME_RANGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import com.wachon.spotiwrap.features.menu.data.repository.TracksRepository
import com.wachon.spotiwrap.features.menu.domain.model.TopItemTimeRange
import com.wachon.spotiwrap.features.menu.domain.model.TopItemType
import com.wachon.spotiwrap.features.menu.domain.model.TrackModel

interface GetUserTopTracksUseCase {
    operator fun invoke(
        limit: Int = DEFAULT_LIMIT,
        offset: Int = DEFAULT_OFFSET,
        timeRange: TopItemTimeRange = DEFAULT_TIME_RANGE
    ): Flow<List<TrackModel>>
}

class GetUserTopTracks(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetUserTopTracksUseCase {

    companion object {
        const val DEFAULT_LIMIT = 10
        const val DEFAULT_OFFSET = 0
        val DEFAULT_TIME_RANGE = TopItemTimeRange.MEDIUM_TERM
    }

    override fun invoke(limit: Int, offset: Int, timeRange: TopItemTimeRange): Flow<List<TrackModel>> {
        return tracksRepository
            .getTopTracks(
                type = TopItemType.TRACKS,
                limit = limit,
                offset = offset,
                timeRange = TopItemTimeRange.MEDIUM_TERM
            )
            .flowOn(dispatchers.background)
    }

}