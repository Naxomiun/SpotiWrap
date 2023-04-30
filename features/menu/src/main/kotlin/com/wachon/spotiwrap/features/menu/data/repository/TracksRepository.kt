package com.wachon.spotiwrap.features.menu.data.repository

import com.wachon.spotiwrap.features.menu.data.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.features.menu.domain.model.TopItemTimeRange
import com.wachon.spotiwrap.features.menu.domain.model.TopItemType
import com.wachon.spotiwrap.features.menu.domain.model.TrackModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TracksRepository {

    fun getTopTracks(
        type: TopItemType,
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>>

}

class DefaultTracksRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : TracksRepository {

    override fun getTopTracks(
        type: TopItemType,
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> {
        return spotifyDatasource.getTopItems(
            type = type.name.lowercase(),
            limit = limit,
            offset = offset,
            timeRange = timeRange.name.lowercase()
        ).map {
            it.items?.map {
                it.toTrackModel()
            }.orEmpty()
        }
    }

}