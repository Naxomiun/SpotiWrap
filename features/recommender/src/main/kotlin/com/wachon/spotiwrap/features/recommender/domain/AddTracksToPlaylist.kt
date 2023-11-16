package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface AddTracksToPlaylistUseCase {
    suspend operator fun invoke(
        playlistId: String,
        tracksUri: List<String>,
    ): Flow<Unit>
}

class AddTracksToPlaylist(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : AddTracksToPlaylistUseCase {
    override suspend fun invoke(playlistId: String, tracksUri: List<String>): Flow<Unit> {
        return playlistRepository.addTrackToPlaylist(
            playlistId = playlistId,
            uris = tracksUri,
        ).flowOn(dispatchers.background)
    }
}