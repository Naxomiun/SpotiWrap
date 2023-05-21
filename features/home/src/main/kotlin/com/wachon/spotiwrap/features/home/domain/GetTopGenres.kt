package com.wachon.spotiwrap.features.home.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.extensions.capitalizeWords
import com.wachon.spotiwrap.core.common.model.ArtistModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

interface GetUserTopGenresFromArtistsUseCase {
    operator fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<TopGenreUI>>
}

class GetTopGenresFromArtists(
    private val dispatchers: DispatcherProvider
) : GetUserTopGenresFromArtistsUseCase {

    override fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<TopGenreUI>> {
        return artistsFlow.flatMapLatest { artists ->

            flow {
                val counts = artists
                    .flatMap { it.genres }
                    .filterNotNull()
                    .filter { it.isNotBlank() }
                    .groupingBy { it }
                    .eachCount()
                    .toList()
                    .sortedByDescending { it.second }
                    .take(5)

                val maxValue = counts.maxOfOrNull { it.second } ?: 0

                val topGenresUI = counts.map { (genre, count) ->
                    TopGenreUI(
                        genreName = genre.capitalizeWords(),
                        genreChartValue = count.toFloat() / maxValue.toFloat()
                    )
                }.sortedByDescending { it.genreChartValue }

                emit(topGenresUI)
            }
        }
            .catch { emit(emptyList()) }
            .flowOn(dispatchers.background)
    }

}
