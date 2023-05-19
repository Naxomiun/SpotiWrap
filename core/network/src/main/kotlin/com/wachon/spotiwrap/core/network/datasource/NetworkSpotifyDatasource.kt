package com.wachon.spotiwrap.core.network.datasource

import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import com.wachon.spotiwrap.core.network.service.SpotifyService
import kotlinx.coroutines.flow.Flow

interface NetworkSpotifyDatasource {

    suspend fun getUserInfo(): UserProfileApi

    suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi

}

class DefaultNetworkSpotifyDatasource(
    private val spotifyService: SpotifyService
) : NetworkSpotifyDatasource {

    override suspend fun getUserInfo(): UserProfileApi {
        return spotifyService
            .getMe()
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

}