package com.wachon.spotiwrap.data.repository

import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.core.database.datasource.ProfileDao
import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.data.extensions.toTrackDB
import com.wachon.spotiwrap.data.worker.Syncable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

interface UserRepository : Syncable {
    fun getUserInfo(): Flow<UserProfileModel?>
}

class DefaultUserRepository(
    private val profileDao: ProfileDao,
    private val spotifyDatasource: NetworkSpotifyDatasource
) : UserRepository {

    override suspend fun sync(): Result<Boolean> {
        return try {
            val apiUserProfile = spotifyDatasource.getUserInfo()
            profileDao.insertProfile(apiUserProfile.toTrackDB())
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getUserInfo(): Flow<UserProfileModel?> {
        return combine(
            profileDao.getProfile(),
            spotifyDatasource.getCurrentTrack()
        ) { profile, currentTrack ->
            val currentTrackModel = currentTrack?.toDomain()
            profile?.toDomain(currentTrackModel)
        }
    }

}
