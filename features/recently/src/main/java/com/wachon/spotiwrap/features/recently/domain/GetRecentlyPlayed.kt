package com.wachon.spotiwrap.features.recently.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.TracksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetRecentlyPlayedUseCase {
    operator fun invoke(): Flow<List<TrackModel>>
}

class GetRecentlyPlayed(
    private val tracksRepository: TracksRepository,
    private val dispatchers: DispatcherProvider
) : GetRecentlyPlayedUseCase {
    override fun invoke(): Flow<List<TrackModel>> {
        return tracksRepository.getRecentlyPlayed().flowOn(dispatchers.background)
    }
}
