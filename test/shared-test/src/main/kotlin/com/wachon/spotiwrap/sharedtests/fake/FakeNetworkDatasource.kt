package com.wachon.spotiwrap.sharedtests.fake

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
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
import com.wachon.spotiwrap.sharedtests.fabricator.ProfileFabricator
import kotlinx.coroutines.flow.Flow

class FakeNetworkDatasource : NetworkSpotifyDatasource {

    var shouldFail = false

    override suspend fun getUserInfo(): UserProfileApi {
        return if (shouldFail) {
            throw Exception("FakeNetworkDatasource.getUserInfo() failed")
        } else {
            ProfileFabricator.getFakeUserProfile()
        }
    }

    override fun getCurrentTrack(): Flow<CurrentTrackApi?> {
        return throw Exception("FakeNetworkDatasource.getCurrentTrack() failed")
    }

    override fun getArtist(id: String): Flow<ArtistApi> {
        return throw Exception("FakeNetworkDatasource.getArtist() failed")
    }

    override fun getArtistsFromTrack(id: String): Flow<TopItemApi> {
        return throw Exception("FakeNetworkDatasource.getArtistsFromTrack() failed")
    }

    override fun getTrack(id: String): Flow<TopItemApi> {
        return throw Exception("FakeNetworkDatasource.getTrack() failed")
    }

    override fun getTrackFeatures(id: String): Flow<TrackFeaturesApi> {
        return throw Exception("FakeNetworkDatasource.getTrackFeatures() failed")
    }

    override fun getArtistAlbums(id: String): Flow<TopAlbumsItemApi> {
        return throw Exception("FakeNetworkDatasource.getArtistAlbums() failed")
    }

    override fun getArtistTopTracks(id: String, market: String): Flow<ArtistTopTracks> {
        return throw Exception("FakeNetworkDatasource.getArtistTopTracks() failed")
    }

    override fun getArtistRelated(id: String): Flow<RelatedArtistApi> {
        return throw Exception("FakeNetworkDatasource.getArtistRelated() failed")
    }

    override suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi {
        return throw Exception("FakeNetworkDatasource.getTopItems() failed")
    }

    override fun getTopItemsFlow(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): Flow<TopApi> {
        return throw Exception("FakeNetworkDatasource.getTopItemsFlow() failed")
    }

    override fun getRecentlyPlayed(): Flow<TopRecentlyItemApi> {
        return throw Exception("FakeNetworkDatasource.getRecentlyPlayed() failed")
    }

    override suspend fun getRecommendations(
        artists: String,
        tracks: String,
        genres: String
    ): Flow<RecommendationsApi> {
        return throw Exception("FakeNetworkDatasource.getRecommendations() failed")
    }

    override suspend fun getGenres(): List<String> {
        return throw Exception("FakeNetworkDatasource.getGenres() failed")
    }

    override suspend fun searchArtist(query: String): SearchedArtistApi {
        return throw Exception("FakeNetworkDatasource.searchArtist() failed")
    }

    override suspend fun searchTrack(query: String): Flow<SearchedTrackApi> {
        return throw Exception("FakeNetworkDatasource.searchTrack() failed")
    }

    override fun getUserPlaylists(): Flow<TopPlaylistApi> {
        return throw Exception("FakeNetworkDatasource.getUserPlaylists() failed")
    }

    override suspend fun getPlaylistItems(id: String): Flow<TopPlaylistItemApi> {
        return throw Exception("FakeNetworkDatasource.getPlaylistItems() failed")
    }

    override suspend fun createPlaylist(
        spotifyId: String,
        name: String,
        description: String
    ): PlaylistApi {
        return throw Exception("FakeNetworkDatasource.createPlaylist() failed")
    }

    override suspend fun addTrackToPlaylist(playlistId: String, uris: List<String>) {
        return throw Exception("FakeNetworkDatasource.addTrackToPlaylist() failed")
    }

    override fun getAlbum(id: String): Flow<AlbumApi> {
        TODO("Not yet implemented")
    }

    override fun getAlbumTracks(id: String): Flow<TopAlbumsItemsApi> {
        TODO("Not yet implemented")
    }

}