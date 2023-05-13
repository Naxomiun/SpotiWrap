package com.wachon.spotiwrap.features.home.domain

import android.util.Log
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

interface GetUserTopGenresFromArtistsUseCase {
    operator fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<String>>
}

class GetTopGenresFromArtists(
    private val dispatchers: DispatcherProvider
) : GetUserTopGenresFromArtistsUseCase {

    override fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<String>> {
        return artistsFlow.map {
            it.flatMap {
                Log.e("CACA", it.genres.toString())
                it.genres
            }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedByDescending { it.second }
                .map { it.first }
        }.flowOn(dispatchers.background)
    }

}

