package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.database.datasource.ArtistDao
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.data.extensions.toArtistDB
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface ArtistsRepository : Syncable {
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

    override suspend fun sync(): Result<Boolean> {
        try {
            val apiItems = spotifyDatasource.getTopItems(
                type = TopItemType.ARTISTS.name.lowercase(),
                limit = 50,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            )

            val dbItems = artistDao.getArtistsNoFlow()

            if (!isContentTheSame(apiItems.items ?: emptyList(), dbItems)) {
                artistDao.insertArtists(
                    compareFame(apiItems.items ?: emptyList(), dbItems)
                        .mapIndexed { index, topItemApi -> topItemApi.toArtistDB(index) }
                )
            }
            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun isContentTheSame(items: List<TopItemApi>, artistsDB: List<ArtistDB>): Boolean {
        if (items.size != artistsDB.size) {
            return false
        }

        for (i in items.indices) {
            val item = items[i]
            val artist = artistsDB[i]

            if (item.name != artist.artistName) {
                return false
            }
        }

        return true
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