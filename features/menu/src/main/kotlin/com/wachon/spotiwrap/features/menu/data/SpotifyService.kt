package com.wachon.spotiwrap.features.menu.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface SpotifyService {

    @GET("/v1/me")
    fun getMe(@Header("Authorization") authHeader: String): Call<User>

}