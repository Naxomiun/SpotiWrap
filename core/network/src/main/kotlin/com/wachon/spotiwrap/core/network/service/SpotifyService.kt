package com.wachon.spotiwrap.core.network.service

import android.util.Log
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
import com.wachon.spotiwrap.core.network.model.GenresApi
import com.wachon.spotiwrap.core.network.model.SearchedArtistApi
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpotifyService(
    private val httpClient: HttpClient
) {

    suspend fun getMe(): UserProfileApi {
        return httpClient.get("/v1/me").body()
    }

    fun getCurrentTrack(): Flow<CurrentTrackApi?> = flow {
        Log.e("SpotifyService", "getCurrentTrack")
        try {
            emit(httpClient.get("v1/me/player/currently-playing").body())
        } catch (e: Exception) {
            emit(null)
        }
    }

    suspend fun getTop(
        type: String,
        limit: Int? = 10,
        offset: Int? = 0,
        timeRange: String
    ): TopApi {
        return httpClient.get("/v1/me/top/$type") {
            parameter("limit", limit)
            parameter("offset", offset)
            parameter("time_range", timeRange)
        }.body()
    }

    suspend fun getGenres(): GenresApi {
        return httpClient.get("/v1/recommendations/available-genre-seeds").body()
    }

    suspend fun searchArtist(query: String): SearchedArtistApi {
        return httpClient.get("/v1/search") {
            parameter("q", query)
            parameter("type", TopItemType.ARTIST.name.lowercase())
            parameter("limit", "3")
        }.body()
    }
}