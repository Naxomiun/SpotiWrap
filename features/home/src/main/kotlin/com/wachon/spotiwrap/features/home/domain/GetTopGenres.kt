package com.wachon.spotiwrap.features.home.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.ArtistModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlin.math.max

interface GetUserTopGenresFromArtistsUseCase {
    operator fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<TopGenreUI>>
}

class GetTopGenresFromArtists(
    private val dispatchers: DispatcherProvider
) : GetUserTopGenresFromArtistsUseCase {

    override fun invoke(artistsFlow: Flow<List<ArtistModel>>): Flow<List<TopGenreUI>> {
        return artistsFlow.map {
            val counts = it.flatMap {
                it.genres
            }
                .groupingBy { it }
                .eachCount()
                .toList()
                .sortedByDescending { it.second }
                .map { it.first
                    .split(' ')
                    .joinToString(" ") {
                        it.replaceFirstChar(Char::uppercaseChar)
                    } to it.second
                }
                .take(5)

            val maxValue = counts.maxOf { it.second }

            counts
                .map {
                    TopGenreUI(
                        genreName = it.first,
                        genreChartValue = it.second.toFloat() / maxValue.toFloat()
                    )
                }
                .sortedByDescending { it.genreChartValue }

        }.flowOn(dispatchers.background)
    }

}

