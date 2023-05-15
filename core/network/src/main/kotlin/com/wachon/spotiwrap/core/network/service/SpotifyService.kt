package com.wachon.spotiwrap.core.network.service

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

}