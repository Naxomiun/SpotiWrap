package com.wachon.spotiwrap.features.profile.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.UserModel
import com.wachon.spotiwrap.features.profile.data.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<UserModel>
}

class GetUserProfile(
    private val userRepository: UserRepository,
    private val dispatchers: DispatcherProvider
) : GetUserProfileUseCase {

    override fun invoke(): Flow<UserModel> {
        return userRepository
            .getUserInfo()
            .flowOn(dispatchers.background)
    }

}