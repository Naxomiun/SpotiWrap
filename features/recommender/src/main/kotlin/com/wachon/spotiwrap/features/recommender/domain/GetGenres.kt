package com.wachon.spotiwrap.features.recommender.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.data.repository.GenresRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetGenresUseCase {
    suspend operator fun invoke(): Flow<List<String>>
}

class GetGenres(
    private val genresRepository: GenresRepository,
    private val dispatchers: DispatcherProvider
) : GetGenresUseCase {
    override suspend fun invoke(): Flow<List<String>> {
        return genresRepository.getGenres().flowOn(dispatchers.background)
    }
}
