package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface SearchTrackUseCase {
    suspend operator fun invoke(query: String): Flow<List<TrackModel>>
}

class SearchTrack(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : SearchTrackUseCase {

    override suspend fun invoke(query: String): Flow<List<TrackModel>> {
        return playlistRepository.searchTrack(query).flowOn(dispatchers.background)
    }

}