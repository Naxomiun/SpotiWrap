package com.wachon.spotiwrap.core.network.datasource

import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
import com.wachon.spotiwrap.core.network.model.SearchedArtistApi
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import com.wachon.spotiwrap.core.network.service.SpotifyService
import kotlinx.coroutines.flow.Flow

interface NetworkSpotifyDatasource {

    suspend fun getUserInfo(): UserProfileApi

    fun getCurrentTrack(): Flow<CurrentTrackApi?>

    suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi

    suspend fun getGenres(): List<String>

    suspend fun searchArtist(query: String): SearchedArtistApi
}

class DefaultNetworkSpotifyDatasource(
    private val spotifyService: SpotifyService
) : NetworkSpotifyDatasource {

    override suspend fun getUserInfo(): UserProfileApi {
        return spotifyService
            .getMe()
    }

    override fun getCurrentTrack(): Flow<CurrentTrackApi?> {
        return spotifyService
            .getCurrentTrack()
    }

    override suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi {
        return spotifyService
            .getTop(
                type = type,
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
    }

    override suspend fun getGenres(): List<String> {
        return spotifyService.getGenres().genres
    }

    override suspend fun searchArtist(query: String): SearchedArtistApi {
        return spotifyService.searchArtist(query = query)
    }

}