package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.extensions.toDomain
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow

interface PlaylistRepository : Syncable {

    suspend fun getUserPlaylists(): Flow<List<PlaylistModel>>

    suspend fun getPlaylistItems(id: String): Flow<List<TrackModel>>

    suspend fun createPlaylist(
        name: String,
        description: String,
    ): Flow<PlaylistModel>

    suspend fun addTrackToPlaylist(
        playlistId: String,
        uris: List<String>,
    ): Flow<Unit>
}

class DefaultPlaylistRepository(
    private val profileDao: ProfileDao,
    private val spotifyDatasource: NetworkSpotifyDatasource,
) : PlaylistRepository {

    override suspend fun sync(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserPlaylists(): Flow<List<PlaylistModel>> {
        return combine(profileDao.getProfile()) { profile ->
            spotifyDatasource.getUserPlaylists().toDomain().filter {
                it.owner.id == (profile.first()?.spotifyId ?: "")
            }
        }
    }

    override suspend fun getPlaylistItems(id: String): Flow<List<TrackModel>> = flow {
        emit(
            spotifyDatasource.getPlaylistItems(id = id).toDomain()
        )
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
}