package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.extensions.toDomain
import com.wachon.spotiwrap.data.extensions.toTrackModel
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

interface AlbumRepository : Syncable {

    fun getAlbum(
        id: String
    ): Flow<AlbumModel>

    fun getAlbumTracks(
        id: String
    ): Flow<List<TrackModel>>

}

class DefaultAlbumRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : AlbumRepository {

    override suspend fun sync(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getAlbum(id: String): Flow<AlbumModel> = combine(
        spotifyDatasource.getAlbum(id = id)
    ) { album ->
        album.first().toDomain()
    }


    override fun getAlbumTracks(id: String): Flow<List<TrackModel>> = combine(
        spotifyDatasource.getAlbumTracks(id = id)
    ) { albumTracks ->
        albumTracks.first().items?.map { it.toTrackModel() } ?: emptyList()
    }

}