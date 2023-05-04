package com.wachon.spotiwrap.features.profile.domain

import android.util.Log
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.features.profile.data.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach

interface GetUserProfileUseCase {
    operator fun invoke(): Flow<UserProfileModel>
}

class GetUserProfile(
    private val userRepository: UserRepository,
    private val dispatchers: DispatcherProvider
) : GetUserProfileUseCase {

    override fun invoke(): Flow<UserProfileModel> {
        return userRepository
            .getUserInfo()
            .flowOn(dispatchers.background)
    }

}