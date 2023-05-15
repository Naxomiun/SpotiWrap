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
                limit = 50,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            )

            val dbItems = trackDao.getTracksNoFlow()

            if (!isContentTheSame(apiItems.items ?: emptyList(), dbItems)) {
                trackDao.insertTracks(
                    compareFame(apiItems.items ?: emptyList(), dbItems)
                        .mapIndexed { index, topItemApi -> topItemApi.toTrackDB(index) }
                )

            }
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun isContentTheSame(items: List<TopItemApi>, tracksDB: List<TrackDB>): Boolean {
        if (items.size != tracksDB.size) {
            return false
        }

        for (i in items.indices) {
            val item = items[i]
            val track = tracksDB[i]

            if (item.name != track.trackTitle) {
                return false
            }
        }

        return true
    }

    private fun compareFame(items: List<TopItemApi>, tracksDB: List<TrackDB>): List<TopItemApi> {
        return items.mapIndexed { index, topItem ->
            tracksDB.find { it.trackId == topItem.id }
                .also { trackDB ->
                    if (tracksDB.isEmpty()) {
                        topItem.fame = ItemFame.NONE
                    } else if (trackDB == null) {
                        topItem.fame = ItemFame.NEW
                    } else {
                        when {
                            index < trackDB.trackIndex -> topItem.fame = ItemFame.UP
                            index == trackDB.trackIndex -> topItem.fame = ItemFame.EVEN
                            index > trackDB.trackIndex -> topItem.fame = ItemFame.DOWN
                        }
                    }
                }
            topItem
        }
    }

    override fun getTopTracks(
        limit: Int, offset: Int, timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> {
        return trackDao.getTracks().map { trackDBList -> trackDBList.map { it.toDomain() } }
    }

}