package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.menu.data.SpotifyService
import com.wachon.spotiwrap.features.menu.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<User>
}

class GetUserProfile(
    private val spotifyService: SpotifyService,
    private val dispatchers: DispatcherProvider
) : GetUserProfileUseCase {

    override fun invoke(): Flow<User> {
        return spotifyService
            .getMe()
            .flowOn(dispatchers.background)
    }

}