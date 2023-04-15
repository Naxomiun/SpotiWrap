package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.presentation.MenuCategory
import java.io.IOException

interface GetUserTopItemsUseCase {
    suspend operator fun invoke(
        type: MenuCategory,
        limit: Int?,
        offset: Int?,
        timeRange: String?
    ): Top?
}

class GetUserTopItems(
    private val spotifyService: SpotifyService
) : GetUserTopItemsUseCase {

    override suspend fun invoke(
        type: MenuCategory,
        limit: Int?,
        offset: Int?,
        timeRange: String?
    ): Top? {

        val call = spotifyService.getTop(
            type = type.name.lowercase(),
            limit = limit,
            offset = offset,
            timeRange = timeRange
        )

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