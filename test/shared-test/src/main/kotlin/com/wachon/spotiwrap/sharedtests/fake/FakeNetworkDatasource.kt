package com.wachon.spotiwrap.sharedtests.fake

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import com.wachon.spotiwrap.sharedtests.fabricator.ProfileFabricator

class FakeNetworkDatasource : NetworkSpotifyDatasource {

    var shouldFail = false

    override suspend fun getUserInfo(): UserProfileApi {
        return if (shouldFail) {
            throw Exception("FakeNetworkDatasource.getUserInfo() failed")
        } else {
            ProfileFabricator.getFakeUserProfile()
        }
    }

    override suspend fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): TopApi {
        return throw Exception("FakeNetworkDatasource.getTopItems() failed")
    }

}