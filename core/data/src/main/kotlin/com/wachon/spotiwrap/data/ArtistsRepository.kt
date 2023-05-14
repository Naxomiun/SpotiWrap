package com.wachon.spotiwrap.data

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.database.datasource.ArtistDao
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.extensions.toArtistDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface ArtistsRepository {
    fun getTopArtists(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>>
}

class DefaultArtistsRepository(
    private val artistDao: ArtistDao,
    private val spotifyDatasource: NetworkSpotifyDatasource
) : ArtistsRepository {

    init {
        sync()
    }

    private fun sync() {
        CoroutineScope(Dispatchers.IO).launch {
            spotifyDatasource.getTopItems(
                type = TopItemType.ARTISTS.name.lowercase(),
                limit = 50,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            ).collect { topApi ->
                artistDao.insertArtists(
                    compareFame(topApi.items ?: emptyList(), artistDao.getArtistsNoFlow())
                        .mapIndexed { index, topItemApi -> topItemApi.toArtistDB(index) }
                )
            }
        }
    }

    private fun compareFame(items: List<TopItemApi>, artistsDB: List<ArtistDB>): List<TopItemApi> {
        return items.mapIndexed { index, topItem ->
            artistsDB.find { it.artistId == topItem.id }
                .also { artistDB ->
                    if (artistsDB.isEmpty()) {
                        topItem.fame = ItemFame.NONE
                    } else if (artistDB == null) {
                        topItem.fame = ItemFame.NEW
                    } else {
                        when {
                            index < artistDB.artistIndex -> topItem.fame = ItemFame.UP
                            index == artistDB.artistIndex -> topItem.fame = ItemFame.EVEN
                            index > artistDB.artistIndex -> topItem.fame = ItemFame.DOWN
                        }
                    }

                }
            topItem
        }
    }

    override fun getTopArtists(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>> {
        return artistDao.getArtists().map { artistDBList -> artistDBList.map { it.toDomain() } }
    }

}