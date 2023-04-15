package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.User
import java.io.IOException

interface GetUserProfileUseCase {
    suspend operator fun invoke(): User?
}

class GetUserProfile(
    private val spotifyService: SpotifyService
) : GetUserProfileUseCase {

    override suspend fun invoke(): User? {
        val call = spotifyService.getMe()
        return try {
            val response = call.execute()
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: IOException) {
            null
        }
    }

}