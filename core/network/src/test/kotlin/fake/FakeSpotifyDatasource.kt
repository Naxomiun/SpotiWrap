package fake

import com.wachon.spotiwrap.core.network.datasource.NetworkSpotifyDatasource
import com.wachon.spotiwrap.core.network.model.TopApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import kotlinx.coroutines.flow.Flow

class FakeSpotifyDatasource : NetworkSpotifyDatasource {

    override fun getUserInfo(): Flow<UserProfileApi> {
        TODO("Not yet implemented")
    }

    override fun getTopItems(
        type: String,
        limit: Int,
        offset: Int,
        timeRange: String
    ): Flow<TopApi> {
        TODO("Not yet implemented")
    }
}