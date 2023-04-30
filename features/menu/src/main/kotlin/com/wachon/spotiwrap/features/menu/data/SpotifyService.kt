package com.wachon.spotiwrap.features.menu.data

import com.wachon.spotiwrap.features.menu.data.model.TopApi
import com.wachon.spotiwrap.features.menu.data.model.UserApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpotifyService(
    private val httpClient: HttpClient
) {

    fun getMe(): Flow<UserApi> = flow {
        emit(httpClient.get("/v1/me").body())
    }

    fun getTop(
        type: String,
        limit: Int? = 10,
        offset: Int? = 0,
        timeRange: String
    ): Flow<TopApi> = flow {
        emit(
            httpClient.get("/v1/me/top/$type") {
                parameter("limit", limit)
                parameter("offset", offset)
                parameter("time_range", timeRange)
            }.body()
        )
    }

}