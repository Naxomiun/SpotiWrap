package com.wachon.spotiwrap.features.profile.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<UserProfileModel?>
}

class GetUserProfile(
    private val userRepository: UserRepository,
    private val dispatchers: DispatcherProvider
) : GetUserProfileUseCase {

    override fun invoke(): Flow<UserProfileModel?> {
        return userRepository
            .getUserInfo()
            .flowOn(dispatchers.background)
    }

}