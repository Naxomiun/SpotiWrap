package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.User
import java.io.IOException

interface GetUserProfileUseCase {
    suspend operator fun invoke(token: String): User?
}

class GetUserProfile(
    private val spotifyService: SpotifyService
) : GetUserProfileUseCase {

    override suspend fun invoke(token: String): User? {
        val call = spotifyService.getMe("Bearer $token")
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