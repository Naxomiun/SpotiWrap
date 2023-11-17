package com.wachon.spotiwrap.sharedtests.fake

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.CurrentTrackApi
import com.wachon.spotiwrap.core.network.model.PlaylistApi
import com.wachon.spotiwrap.core.network.model.RecommendationsApi
import com.wachon.spotiwrap.core.network.model.SearchedArtistApi
import com.wachon.spotiwrap.core.network.model.SearchedTrackApi
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistItemApi
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

    override suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi {
        return throw Exception("FakeNetworkDatasource.getTopItems() failed")
    }

    override fun getRecentlyPlayed(): Flow<TopPlaylistItemApi> {
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

    override suspend fun getUserPlaylists(): Flow<TopPlaylistApi> {
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

}