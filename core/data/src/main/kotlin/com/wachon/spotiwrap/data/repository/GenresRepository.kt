package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GenresRepository : Syncable {
    suspend fun getGenres(): Flow<List<String>>
}

class DefaultGenresRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : GenresRepository {

    override suspend fun sync(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getGenres(): Flow<List<String>> = flow {
        emit(spotifyDatasource.getGenres())
    }
}