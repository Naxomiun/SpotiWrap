package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.extensions.toArtistModel
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchRepository : Syncable {
    suspend fun searchArtist(query: String): Flow<List<ArtistModel>>
}

class DefaultSearchRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : SearchRepository {

    override suspend fun sync(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun searchArtist(query: String): Flow<List<ArtistModel>> = flow {
        emit(spotifyDatasource.searchArtist(query = query).artists.items?.map { it.toArtistModel() } ?: emptyList())
    }
}