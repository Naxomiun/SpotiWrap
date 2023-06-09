package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.datasource.TrackDao
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.data.extensions.toTrackDB
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface TracksRepository : Syncable {
    fun getTopTracks(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>>
}

class DefaultTracksRepository(
    private val trackDao: TrackDao,
    private val spotifyDatasource: NetworkSpotifyDatasource
) : TracksRepository {

    override suspend fun sync(): Result<Boolean> {
        try {
            val apiItems = spotifyDatasource.getTopItems(
                type = TopItemType.TRACKS.name.lowercase(),
                limit = 25,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            )

            val dbItems = trackDao.getTracksNoFlow()

            trackDao.insertTracks(
                mapTopItemsToTrackDB(apiItems.items ?: emptyList(), dbItems)
            )

            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun getTopTracks(
        limit: Int, offset: Int, timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> {
        return trackDao
            .getTracks()
            .map { trackDBList ->
                trackDBList.map { it.toDomain() }
            }
    }

    private fun mapTopItemsToTrackDB(items: List<TopItemApi>, tracksDB: List<TrackDB>): List<TrackDB> {
        return items.mapIndexed { index, topItem ->
            val trackDB = tracksDB.find { it.trackId == topItem.id }
            topItem.toTrackDB(
                index = index,
                fame = when {
                    tracksDB.isEmpty() -> ItemFame.NONE
                    trackDB == null -> ItemFame.NEW
                    index < trackDB.trackIndex -> ItemFame.UP
                    index > trackDB.trackIndex -> ItemFame.DOWN
                    else -> ItemFame.NONE
                }
            )
        }
    }

}