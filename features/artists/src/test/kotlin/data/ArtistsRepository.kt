package data

import com.wachon.spotiwrap.features.artists.data.ArtistsRepository
import com.wachon.spotiwrap.features.artists.data.DefaultArtistsRepository
import com.wachon.spotiwrap.sharedtests.fake.FakeNetworkDatasource
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ArtistsRepositoryTest {

    private lateinit var SUT: ArtistsRepository
    private val fakeNetworkDatasource = FakeNetworkDatasource()

    @Before
    fun setup() {
        SUT = DefaultArtistsRepository(fakeNetworkDatasource)
    }

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(4, 2 + 2)
    }

}
