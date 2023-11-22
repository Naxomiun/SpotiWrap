package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetPlaylistItemsUseCase {
    suspend operator fun invoke(id: String): Flow<List<TrackModel>>
}

class GetPlaylistItems(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : GetPlaylistItemsUseCase {
    override suspend fun invoke(id: String): Flow<List<TrackModel>> {
        return playlistRepository.getPlaylistItems(id = id).flowOn(dispatchers.background)
    }
}