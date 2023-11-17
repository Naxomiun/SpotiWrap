package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.data.repository.PlaylistRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetRecommendationsUseCase {
    suspend operator fun invoke(
        artists: String = "",
        tracks: String = "",
        genres: String = ""
    ): Flow<List<TrackModel>>
}

class GetRecommendations(
    private val playlistRepository: PlaylistRepository,
    private val dispatchers: DispatcherProvider,
) : GetRecommendationsUseCase {
    override suspend fun invoke(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<List<TrackModel>> {
        return playlistRepository.getRecommendations(artists, tracks, genres)
            .flowOn(dispatchers.background)
    }
}