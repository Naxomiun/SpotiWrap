package com.wachon.spotiwrap.data

import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.datasource.TrackDao
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.extensions.toTrackDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface TracksRepository {
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

    init {
        sync()
    }

    private fun sync() {
        CoroutineScope(Dispatchers.IO).launch {
            spotifyDatasource.getTopItems(
                type = TopItemType.TRACKS.name.lowercase(),
                limit = 10,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            ).collect { topApi ->
                trackDao.insertTracks(
                    compareFame(topApi.items ?: emptyList(), trackDao.getTracksNoFlow())
                        .mapIndexed { index, topItemApi -> topItemApi.toTrackDB(index) }
                )
            }
        }
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