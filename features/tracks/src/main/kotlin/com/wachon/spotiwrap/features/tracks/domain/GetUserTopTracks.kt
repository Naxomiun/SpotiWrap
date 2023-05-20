package com.wachon.spotiwrap.features.tracks.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks.Companion.DEFAULT_LIMIT
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks.Companion.DEFAULT_OFFSET
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracks.Companion.DEFAULT_TIME_RANGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

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
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
            .flowOn(dispatchers.default)
    }

}