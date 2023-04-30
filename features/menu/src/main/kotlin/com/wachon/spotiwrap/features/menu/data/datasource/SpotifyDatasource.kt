package com.wachon.spotiwrap.features.menu.data.datasource

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.model.TopApi
import com.wachon.spotiwrap.features.menu.data.model.UserApi
import kotlinx.coroutines.flow.Flow

interface NetworkSpotifyDatasource {

    fun getUserInfo(): Flow<UserApi>

    fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): Flow<TopApi>

}

class DefaultNetworkSpotifyDatasource(
    private val spotifyService: SpotifyService
) : NetworkSpotifyDatasource {

    override fun getUserInfo(): Flow<UserApi> {
        return spotifyService
            .getMe()
    }

    override fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): Flow<TopApi> {
        return spotifyService
            .getTop(
                type = type,
                limit = limit,
                offset = offset,
                timeRange = timeRange
            )
    }

}