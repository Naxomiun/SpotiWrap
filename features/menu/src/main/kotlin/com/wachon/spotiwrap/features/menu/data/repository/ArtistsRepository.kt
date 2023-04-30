package com.wachon.spotiwrap.features.menu.data.repository

import com.wachon.spotiwrap.features.menu.data.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.features.menu.domain.model.ArtistModel
import com.wachon.spotiwrap.features.menu.domain.model.TopItemTimeRange
import com.wachon.spotiwrap.features.menu.domain.model.TopItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ArtistsRepository {

    fun getTopArtists(
        type: TopItemType,
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>>

}

class DefaultArtistsRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : ArtistsRepository {

    override fun getTopArtists(
        type: TopItemType,
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>> {
        return spotifyDatasource.getTopItems(
            type = type.name.lowercase(),
            limit = limit,
            offset = offset,
            timeRange = timeRange.name.lowercase()
        ).map {
            it.items?.map {
                it.toArtistModel()
            }.orEmpty()
        }
    }

}