package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


interface GetUserPlaylistsUseCase {
    suspend operator fun invoke(): Flow<List<PlaylistModel>>
}

class GetUserPlaylists(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : GetUserPlaylistsUseCase {
    override suspend fun invoke(): Flow<List<PlaylistModel>> {
        return playlistRepository.getUserPlaylists().flowOn(dispatchers.background)
    }
}