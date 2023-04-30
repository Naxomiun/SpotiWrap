package com.wachon.spotiwrap.features.menu.domain

import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.menu.data.repository.UserRepository
import com.wachon.spotiwrap.features.menu.domain.model.UserModel
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