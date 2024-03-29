package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TrackFeaturesModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.database.datasource.TrackDao
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.data.extensions.toArtistModel
import com.wachon.spotiwrap.data.extensions.toDomain
import com.wachon.spotiwrap.data.extensions.toTrackDB
import com.wachon.spotiwrap.data.extensions.toTrackFeatureModel
import com.wachon.spotiwrap.data.extensions.toTrackModel
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

interface TracksRepository : Syncable {

    fun getArtistFromTrack(
        id: String
    ): Flow<List<ArtistModel>>

    fun getTrack(
        id: String
    ): Flow<TrackModel>

    fun getTrackFeatures(
        id: String
    ): Flow<TrackFeaturesModel>

    fun getArtistAlbums(
        id: String
    ): Flow<List<AlbumModel>>

    fun getArtistTopTracks(
        id: String
    ): Flow<List<TrackModel>>

    fun getArtistRelated(
        id: String
    ): Flow<List<ArtistModel>>

    fun getTopTracks(
        limit: Int,
        offset: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>>

    fun getTopTracksFlow(
        limit: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>>

    fun getRecentlyPlayed(): Flow<List<TrackModel>>
}

class DefaultTracksRepository(
    private val profileDao: ProfileDao,
    private val trackDao: TrackDao,
    private val spotifyDatasource: NetworkSpotifyDatasource
) : TracksRepository {

    override suspend fun sync(): Result<Boolean> {
        try {
            val apiItems = spotifyDatasource.getTopItems(
                type = TopItemType.TRACKS.name.lowercase(),
                limit = 50,
                offset = 0,
                timeRange = TopItemTimeRange.SHORT_TERM.name.lowercase()
            )

            val dbItems = trackDao.getTracksNoFlow()

            trackDao.deleteAll()
            trackDao.insertTracks(
                mapTopItemsToTrackDB(apiItems.items ?: emptyList(), dbItems)
            )

            return Result.success(true)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun getArtistFromTrack(id: String): Flow<List<ArtistModel>> = combine(
        spotifyDatasource.getArtistsFromTrack(
            id = id
        )
    ) { trackApi ->
        trackApi.first().artists?.map { it.toArtistModel() } ?: emptyList()
    }

    override fun getTrack(id: String): Flow<TrackModel> = combine(
        spotifyDatasource.getTrack(
            id = id
        )
    ) { trackApi ->
        trackApi.first().toTrackModel()
    }

    override fun getTrackFeatures(id: String): Flow<TrackFeaturesModel> = combine(
        spotifyDatasource.getTrackFeatures(
            id = id
        )
    ) { trackFeatureApi ->
        trackFeatureApi.first().toTrackFeatureModel()
    }

    override fun getArtistAlbums(id: String): Flow<List<AlbumModel>> = combine(
        spotifyDatasource.getArtistAlbums(
            id = id
        )
    ) { artistAlbums ->
        artistAlbums.first().items?.map { it.toDomain() } ?: emptyList()
    }

    override fun getArtistTopTracks(id: String): Flow<List<TrackModel>> =
        combine(profileDao.getProfile()) { profile ->
            spotifyDatasource.getArtistTopTracks(
                id = id,
                market = profile.first()?.country ?: ""
            ).first().tracks.map { it.toTrackModel() }
        }

    override fun getArtistRelated(id: String): Flow<List<ArtistModel>> = combine(
        spotifyDatasource.getArtistRelated(
            id = id
        )
    ) { artistRelate ->
        artistRelate.first().artists?.map { it.toArtistModel() } ?: emptyList()
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

    override fun getTopTracksFlow(
        limit: Int,
        timeRange: TopItemTimeRange
    ): Flow<List<TrackModel>> =
        combine(
            spotifyDatasource.getTopItemsFlow(
                type = TopItemType.TRACKS.name.lowercase(),
                limit = limit,
                offset = 0,
                timeRange = timeRange.name.lowercase()
            )
        ) { topApi ->
            topApi.first().items?.map { it.toTrackModel() } ?: emptyList()
        }


    private fun mapTopItemsToTrackDB(
        items: List<TopItemApi>,
        tracksDB: List<TrackDB>
    ): List<TrackDB> {
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

    override fun getRecentlyPlayed(): Flow<List<TrackModel>> =
        combine(spotifyDatasource.getRecentlyPlayed()) { recentlyPlayedApi ->
            recentlyPlayedApi.first().toDomain()
        }
}