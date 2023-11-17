package com.wachon.spotiwrap.core.network.service

import android.util.Log
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.network.model.AddTrackRequest
import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
import com.wachon.spotiwrap.core.network.model.GenresApi
import com.wachon.spotiwrap.core.network.model.PlaylistApi
import com.wachon.spotiwrap.core.network.model.RecommendationsApi
import com.wachon.spotiwrap.core.network.model.SearchedArtistApi
import com.wachon.spotiwrap.core.network.model.SearchedTrackApi
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistItemApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.content.TextContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SpotifyService(
    private val httpClient: HttpClient,
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

    fun getRecentlyPlayed(): Flow<TopPlaylistItemApi> = flow {
        emit(httpClient.get("v1/me/player/recently-played").body())
    }

    suspend fun getTop(
        type: String,
        limit: Int? = 10,
        offset: Int? = 0,
        timeRange: String,
    ): TopApi {
        return httpClient.get("/v1/me/top/$type") {
            parameter("limit", limit)
            parameter("offset", offset)
            parameter("time_range", timeRange)
        }.body()
    }

    suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<RecommendationsApi> = flow {
        try {
            emit(httpClient.get("/v1/recommendations") {
                if (artists.isNotBlank()) parameter("seed_artists", artists)
                if (tracks.isNotBlank()) parameter("seed_tracks", tracks)
                if (genres.isNotBlank()) parameter("seed_genres", genres)
            }.body())
        } catch (e: Exception) {
            emit(RecommendationsApi(tracks = mutableListOf()))
        }
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

    suspend fun searchTrack(query: String): Flow<SearchedTrackApi> = flow {
        emit(httpClient.get("/v1/search") {
            parameter("q", query)
            parameter("type", TopItemType.TRACK.name.lowercase())
            parameter("limit", "3")
        }.body())
    }

    suspend fun getUserPlaylists(): Flow<TopPlaylistApi> = flow {
        emit(httpClient.get("/v1/me/playlists").body())
    }

    suspend fun getPlaylistItems(id: String): Flow<TopPlaylistItemApi> = flow {
        emit(httpClient.get("/v1/playlists/$id/tracks").body())
    }

    suspend fun createPlaylist(
        clientId: String,
        name: String,
        description: String,
    ): PlaylistApi {
        val playlistData = mapOf(
            "name" to name,
            "description" to description,
        ).mapValues { (_, value) ->
            Json.encodeToString(value)
        }

        return httpClient.post("/v1/users/${clientId}/playlists") {
            setBody(
                TextContent(
                    Json.encodeToString(playlistData),
                    ContentType.Application.Json
                )
            )
        }.body()
    }

    suspend fun addTrackToPlaylist(playlistId: String, uris: List<String>) {
        val requestBody = AddTrackRequest(uris = uris, 0)
        return httpClient.post("/v1/playlists/$playlistId/tracks") {
            setBody(
                TextContent(
                    Json.encodeToString(requestBody),
                    ContentType.Application.Json
                )
            )
        }.body()
    }
}