package com.wachon.spotiwrap.core.network.service

import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.network.model.AddTrackRequest
import com.wachon.spotiwrap.core.network.model.AlbumApi
import com.wachon.spotiwrap.core.network.model.ArtistApi
import com.wachon.spotiwrap.core.network.model.ArtistTopTracks
import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
import com.wachon.spotiwrap.core.network.model.GenresApi
import com.wachon.spotiwrap.core.network.model.PlaylistApi
import com.wachon.spotiwrap.core.network.model.RecommendationsApi
import com.wachon.spotiwrap.core.network.model.RelatedArtistApi
import com.wachon.spotiwrap.core.network.model.SearchedArtistApi
import com.wachon.spotiwrap.core.network.model.SearchedTrackApi
import com.wachon.spotiwrap.core.network.model.TopAlbumsItemApi
import com.wachon.spotiwrap.core.network.model.TopAlbumsItemsApi
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistItemApi
import com.wachon.spotiwrap.core.network.model.TopRecentlyItemApi
import com.wachon.spotiwrap.core.network.model.TrackFeaturesApi
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
        try {
            emit(httpClient.get("v1/me/player/currently-playing").body())
        } catch (e: Exception) {
            emit(null)
        }
    }

    fun getRecentlyPlayed(): Flow<TopRecentlyItemApi> = flow {
        emit(httpClient.get("v1/me/player/recently-played").body())
    }

    fun getArtist(id: String): Flow<ArtistApi> = flow {
        emit(httpClient.get("/v1/artists/$id").body())
    }

    fun getArtistsFromTrack(id: String): Flow<TopItemApi> = flow {
        emit(httpClient.get("/v1/tracks/$id").body())
    }

    fun getTrack(id: String): Flow<TopItemApi> = flow {
        emit(httpClient.get("/v1/tracks/$id").body())
    }

    fun getTrackFeatures(id: String): Flow<TrackFeaturesApi> = flow {
        emit(httpClient.get("/v1/audio-features/$id").body())
    }

    fun getArtistAlbums(id: String): Flow<TopAlbumsItemApi> = flow {
        emit(httpClient.get("/v1/artists/$id/albums").body())
    }

    fun getArtistTopTracks(id: String, market: String): Flow<ArtistTopTracks> = flow {
        emit(httpClient.get("/v1/artists/$id/top-tracks") {
            parameter("market", market)
        }.body())
    }

    fun getArtistRelated(id: String): Flow<RelatedArtistApi> = flow {
        emit(httpClient.get("/v1/artists/$id/related-artists").body())
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

    fun getTopFlow(
        type: String,
        limit: Int? = 10,
        offset: Int? = 0,
        timeRange: String,
    ): Flow<TopApi> = flow {
        emit(httpClient.get("/v1/me/top/$type") {
            parameter("limit", limit)
            parameter("offset", offset)
            parameter("time_range", timeRange)
        }.body())
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

    fun getUserPlaylists(): Flow<TopPlaylistApi> = flow {
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

    fun getAlbum(id: String): Flow<AlbumApi> = flow {
        emit(httpClient.get("/v1/albums/$id").body())
    }

    fun getAlbumTracks(id: String): Flow<TopAlbumsItemsApi> = flow {
        emit(httpClient.get("/v1/albums/$id/tracks").body())
    }
}