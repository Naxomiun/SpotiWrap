package com.wachon.spotiwrap.features.artists.data

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ArtistsRepository {

    fun getTopArtists(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>>

}

class DefaultArtistsRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : ArtistsRepository {

    override fun getTopArtists(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>> {
        return spotifyDatasource.getTopItems(
            type = TopItemType.ARTISTS.name.lowercase(),
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