package com.wachon.spotiwrap.core.network.datasource

import com.wachon.spotiwrap.core.network.model.AlbumApi
import com.wachon.spotiwrap.core.network.model.ArtistApi
import com.wachon.spotiwrap.core.network.model.ArtistTopTracks
import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
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
import com.wachon.spotiwrap.core.network.service.SpotifyService
import kotlinx.coroutines.flow.Flow

interface NetworkSpotifyDatasource {

    suspend fun getUserInfo(): UserProfileApi

    fun getCurrentTrack(): Flow<CurrentTrackApi?>

    fun getArtist(
        id: String
    ): Flow<ArtistApi>

    fun getArtistsFromTrack(
        id: String
    ): Flow<TopItemApi>

    fun getTrack(
        id: String
    ): Flow<TopItemApi>

    fun getTrackFeatures(
        id: String
    ): Flow<TrackFeaturesApi>

    fun getArtistAlbums(
        id: String
    ): Flow<TopAlbumsItemApi>

    fun getArtistTopTracks(
        id: String,
        market: String
    ): Flow<ArtistTopTracks>

    fun getArtistRelated(
        id: String
    ): Flow<RelatedArtistApi>

    suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String,
    ): TopApi

    fun getTopItemsFlow(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String,
    ): Flow<TopApi>

    fun getRecentlyPlayed(): Flow<TopRecentlyItemApi>

    suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<RecommendationsApi>

    suspend fun getGenres(): List<String>

    suspend fun searchArtist(query: String): SearchedArtistApi

    suspend fun searchTrack(query: String): Flow<SearchedTrackApi>

    fun getUserPlaylists(): Flow<TopPlaylistApi>

    suspend fun getPlaylistItems(id: String): Flow<TopPlaylistItemApi>

    suspend fun createPlaylist(
        spotifyId: String,
        name: String,
        description: String,
    ): PlaylistApi

    suspend fun addTrackToPlaylist(
        playlistId: String,
        uris: List<String>,
    )

    fun getAlbum(id: String): Flow<AlbumApi>

    fun getAlbumTracks(id: String): Flow<TopAlbumsItemsApi>
}

class DefaultNetworkSpotifyDatasource(
    private val spotifyService: SpotifyService,
) : NetworkSpotifyDatasource {

    override suspend fun getUserInfo(): UserProfileApi {
        return spotifyService
            .getMe()
    }

    override fun getCurrentTrack(): Flow<CurrentTrackApi?> {
        return spotifyService
            .getCurrentTrack()
    }

    override fun getArtist(
        id: String

    ): Flow<ArtistApi> {
        return spotifyService
            .getArtist(id = id)
    }

    override fun getArtistsFromTrack(id: String): Flow<TopItemApi> {
        return spotifyService
            .getArtistsFromTrack(id = id)
    }

    override fun getTrack(id: String): Flow<TopItemApi> {
        return spotifyService
            .getTrack(id = id)
    }

    override fun getTrackFeatures(id: String): Flow<TrackFeaturesApi> {
        return spotifyService
            .getTrackFeatures(id = id)
    }

    override fun getArtistAlbums(id: String): Flow<TopAlbumsItemApi> {
        return spotifyService
            .getArtistAlbums(id = id)
    }

    override fun getArtistTopTracks(id: String, market: String): Flow<ArtistTopTracks> {
        return spotifyService
            .getArtistTopTracks(id = id, market = market)
    }

    override fun getArtistRelated(id: String): Flow<RelatedArtistApi> {
        return spotifyService
            .getArtistRelated(id = id)
    }

    override suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String,
    ): TopApi {
        return spotifyService
            .getTop(
                type = type,
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
    }

    override fun getTopItemsFlow(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String,
    ): Flow<TopApi> {
        return spotifyService
            .getTopFlow(
                type = type,
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
    }

    override fun getRecentlyPlayed(): Flow<TopRecentlyItemApi> {
        return spotifyService.getRecentlyPlayed()
    }

    override suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<RecommendationsApi> {
        return spotifyService.getRecommendations(artists, tracks, genres)
    }

    override suspend fun getGenres(): List<String> {
        return spotifyService.getGenres().genres
    }

    override suspend fun searchArtist(query: String): SearchedArtistApi {
        return spotifyService.searchArtist(query = query)
    }

    override suspend fun searchTrack(query: String): Flow<SearchedTrackApi> {
        return spotifyService.searchTrack(query = query)
    }

    override fun getUserPlaylists(): Flow<TopPlaylistApi> {
        return spotifyService.getUserPlaylists()
    }

    override suspend fun getPlaylistItems(id: String): Flow<TopPlaylistItemApi> {
        return spotifyService.getPlaylistItems(id = id)
    }

    override suspend fun createPlaylist(
        spotifyId: String,
        name: String,
        description: String,
    ): PlaylistApi {
        return spotifyService.createPlaylist(
            clientId = spotifyId,
            name = name,
            description = description
        )
    }

    override suspend fun addTrackToPlaylist(playlistId: String, uris: List<String>) {
        return spotifyService.addTrackToPlaylist(playlistId = playlistId, uris = uris)
    }

    override fun getAlbum(id: String): Flow<AlbumApi> {
        return spotifyService.getAlbum(id = id)
    }

    override fun getAlbumTracks(id: String): Flow<TopAlbumsItemsApi> {
        return spotifyService.getAlbumTracks(id = id)
    }

}