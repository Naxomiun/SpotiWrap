package com.wachon.spotiwrap.features.menu.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyService {

    @GET("/v1/me")
    fun getMe(): Call<User>

    @GET("/v1/me/top/{type}")
    fun getTop(
        @Path("type") type: String,
        @Query("limit") limit: Int? = 5,
        @Query("offset") offset: Int? = 0,
        @Query("time_range") timeRange: String? = "medium_term"
    ): Call<Top>

}