package com.wachon.spotiwrap.features.profile.data

import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

interface UserRepository {
    fun getUserInfo(): Flow<UserProfileModel>
}

class DefaultUserRepository(
    private val profileDao: ProfileDao,
    private val spotifyDatasource: NetworkSpotifyDatasource
) : UserRepository {

    init {
        sync()
    }

    private fun sync() {
        spotifyDatasource
            .getUserInfo()
            .map { it.toDomain() }
            .onEach {
                profileDao.insertProfile(it.toDB())
            }
    }

    override fun getUserInfo(): Flow<UserProfileModel> = profileDao
        .getProfile()
        .map { it.toDomain() }
        .flatMapConcat { userProfileModel ->
            flowOf(userProfileModel)
        }

    private fun UserProfileModel.toDB() = UserProfileDB(email, displayName, country, image)

}
