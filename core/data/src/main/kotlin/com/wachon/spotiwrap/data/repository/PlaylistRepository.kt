package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.extensions.toDomain
import com.wachon.spotiwrap.data.extensions.toTrackModel
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

interface PlaylistRepository : Syncable {

    suspend fun createPlaylist(
        name: String,
        description: String,
    ): Flow<PlaylistModel>

    suspend fun getUserPlaylists(): Flow<List<PlaylistModel>>

    suspend fun getPlaylistItems(id: String): Flow<List<TrackModel>>

    suspend fun addTrackToPlaylist(
        playlistId: String,
        uris: List<String>,
    ): Flow<Unit>

    suspend fun searchTrack(query: String): Flow<List<TrackModel>>

    suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<List<TrackModel>>
}

class DefaultPlaylistRepository(
    private val profileDao: ProfileDao,
    private val spotifyDatasource: NetworkSpotifyDatasource,
) : PlaylistRepository {

    override suspend fun sync(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun createPlaylist(
        name: String,
        description: String,
    ): Flow<PlaylistModel> =
        combine(profileDao.getProfile()) { profile ->
            spotifyDatasource.createPlaylist(
                spotifyId = profile.first()?.spotifyId ?: "",
                name = name,
                description = description,
            ).toDomain()
        }

    override suspend fun getUserPlaylists(): Flow<List<PlaylistModel>> =
        combine(
            profileDao.getProfile(),
            spotifyDatasource.getUserPlaylists()
        ) { profile, userPlaylists ->
            userPlaylists.toDomain().filter {
                it.owner.id == (profile?.spotifyId ?: "")
            }
        }

    override suspend fun getPlaylistItems(id: String): Flow<List<TrackModel>> =
        combine(spotifyDatasource.getPlaylistItems(id = id)) { playlistApi ->
            playlistApi.first().toDomain()
        }

    override suspend fun addTrackToPlaylist(
        playlistId: String,
        uris: List<String>,
    ): Flow<Unit> = flow {
        emit(
            spotifyDatasource.addTrackToPlaylist(
                playlistId = playlistId,
                uris = uris
            )
        )
    }

    override suspend fun searchTrack(query: String): Flow<List<TrackModel>> =
        combine(spotifyDatasource.searchTrack(query = query)) { searchedTrackApi ->
            searchedTrackApi.first().tracks.items?.map { it.toTrackModel() }
                ?: emptyList()
        }

    override suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<List<TrackModel>> =
        combine(
            spotifyDatasource.getRecommendations(artists, tracks, genres)
        ) { recommendations ->
            recommendations.first().tracks.map { it.toTrackModel() }
        }
}