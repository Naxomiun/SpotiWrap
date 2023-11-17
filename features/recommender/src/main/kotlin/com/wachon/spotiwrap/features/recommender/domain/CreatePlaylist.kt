package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface CreatePlaylistUseCase {
    suspend operator fun invoke(name: String, description: String): Flow<PlaylistModel>
}

class CreatePlaylist(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : CreatePlaylistUseCase {
    override suspend fun invoke(name: String, description: String): Flow<PlaylistModel> {
        return playlistRepository.createPlaylist(name, description)
            .flowOn(dispatchers.background)
    }
}