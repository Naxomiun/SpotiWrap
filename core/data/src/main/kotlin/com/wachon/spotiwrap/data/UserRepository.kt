package com.wachon.spotiwrap.data

import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.extensions.toTrackDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface UserRepository {
    fun getUserInfo(): Flow<UserProfileModel>
}

class DefaultUserRepository(
    private val profileDao: ProfileDao, private val spotifyDatasource: NetworkSpotifyDatasource
) : UserRepository {

    init {
        sync()
    }

    private fun sync() {
        CoroutineScope(Dispatchers.IO).launch {
            spotifyDatasource.getUserInfo().collect { profileDao.insertProfile(it.toTrackDB()) }
        }
    }

    override fun getUserInfo(): Flow<UserProfileModel> = profileDao.getProfile().map { it.toDomain() }.catch { }

}