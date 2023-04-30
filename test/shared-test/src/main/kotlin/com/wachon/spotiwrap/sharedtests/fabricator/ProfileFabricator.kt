package com.wachon.spotiwrap.sharedtests.fabricator

import com.wachon.spotiwrap.core.network.model.ExplicitContentApi
import com.wachon.spotiwrap.core.network.model.ExternalUrlsApi
import com.wachon.spotiwrap.core.network.model.FollowersApi
import com.wachon.spotiwrap.core.network.model.ImageApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi

object ProfileFabricator {

    fun getFakeUserProfile(): UserProfileApi {
        return UserProfileApi(
            country = "USA",
            displayName = "John Doe",
            email = "johndoe@example.com",
            explicitContent = ExplicitContentApi(true, true),
            externalUrls = ExternalUrlsApi("https://www.example.com"),
            followers = FollowersApi(href = null, 5000),
            href = "https://api.example.com/users/12345",
            id = "12345",
            images = listOf(
                ImageApi("https://www.example.com/profile_image.jpg", 640, 640),
                ImageApi("https://www.example.com/profile_image_thumb.jpg", 64, 64)
            ),
            product = "premium",
            type = "user",
            uri = "spotify:user:12345"
        )
    }

}