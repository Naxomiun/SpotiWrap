package com.wachon.spotiwrap.features.tracks.data

import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.common.model.TrackModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TracksRepository {

    fun getTopTracks(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>>

}

class DefaultTracksRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : TracksRepository {

    override fun getTopTracks(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> {
        return spotifyDatasource.getTopItems(
            type = TopItemType.TRACKS.name.lowercase(),
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