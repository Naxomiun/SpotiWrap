package com.wachon.spotiwrap.features.menu.data.repository

import com.wachon.spotiwrap.features.menu.data.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.features.menu.domain.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserRepository {
    fun getUserInfo(): Flow<UserModel>
}

class DefaultUserRepository(
    private val spotifyDatasource: NetworkSpotifyDatasource
) : UserRepository {

    override fun getUserInfo(): Flow<UserModel> {
        return spotifyDatasource
            .getUserInfo()
            .map { it.toDomain() }
    }

}