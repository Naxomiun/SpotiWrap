package com.wachon.spotiwrap.sharedtests.fake

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import com.wachon.spotiwrap.sharedtests.fabricator.ProfileFabricator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNetworkDatasource: NetworkSpotifyDatasource {

    var shouldFail = false

    override fun getUserInfo(): Flow<UserProfileApi> {
        return flow {
            if(shouldFail) throw Exception("FakeNetworkDatasource.getUserInfo() failed")
            emit(ProfileFabricator.getFakeUserProfile())
        }
    }

    override fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): Flow<TopApi> {
        return flow {
            if(shouldFail) throw Exception("FakeNetworkDatasource.getTopItems() failed")
        }
    }

}