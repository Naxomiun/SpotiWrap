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
                limit = 10,
                offset = 0,
                timeRange = TopItemTimeRange.MEDIUM_TERM.name.lowercase()
            )

            val dbItems = artistDao.getArtistsNoFlow()
            artistDao.insertArtists(
                mapTopItemsToArtistDB(apiItems.items ?: emptyList(), dbItems)
            )

            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    private fun mapTopItemsToArtistDB(items: List<TopItemApi>, artistsDB: List<ArtistDB>): List<ArtistDB> {
        return items.mapIndexed { index, topItem ->
            val artistDB = artistsDB.find { it.artistId == topItem.id }
            topItem.toArtistDB(
                index = index,
                fame = when {
                    artistsDB.isEmpty() -> ItemFame.NONE
                    artistDB == null -> ItemFame.NEW
                    index < artistDB.artistIndex -> ItemFame.UP
                    index > artistDB.artistIndex -> ItemFame.DOWN
                    else -> ItemFame.NONE
                }
            )
        }
    }

    override fun getTopArtists(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<ArtistModel>> {
        return artistDao
            .getArtists()
            .map { artistDBList ->
                artistDBList.map { it.toDomain() }
            }
    }

}