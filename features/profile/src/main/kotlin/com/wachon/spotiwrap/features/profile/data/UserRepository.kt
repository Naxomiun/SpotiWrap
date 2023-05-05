package com.wachon.spotiwrap.features.profile.data

import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserRepository {
    fun getUserInfo(): Flow<UserProfileModel>
}

class DefaultUserRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : UserRepository {

    override fun getUserInfo(): Flow<UserProfileModel> {
        return spotifyDatasource
            .getUserInfo()
            .map { it.toDomain() }
    }

}