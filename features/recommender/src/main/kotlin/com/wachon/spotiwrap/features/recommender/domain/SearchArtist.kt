package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.data.repository.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface SearchArtistUseCase {
    suspend operator fun invoke(query: String): Flow<List<ArtistModel>>
}

class SearchArtist(
    private val artistsRepository: ArtistsRepository,
    private val dispatchers: DispatcherProvider
) : SearchArtistUseCase {
    override suspend fun invoke(query: String): Flow<List<ArtistModel>> {
        return artistsRepository.searchArtist(query).flowOn(dispatchers.background)
    }
}