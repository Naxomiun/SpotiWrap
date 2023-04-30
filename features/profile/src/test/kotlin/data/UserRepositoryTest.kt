package data

import com.wachon.spotiwrap.features.profile.data.DefaultUserRepository
import com.wachon.spotiwrap.features.profile.data.UserRepository
import com.wachon.spotiwrap.sharedtests.fake.FakeNetworkDatasource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {

    private lateinit var SUT: UserRepository
    private val fakeNetworkDatasource = FakeNetworkDatasource()

    @Before
    fun setup() {
        SUT = DefaultUserRepository(fakeNetworkDatasource)
    }

    @Test
    fun when_datasource_emits_then_its_mapped_and_emitted_successfully() = runTest {
        fakeNetworkDatasource.shouldFail = false
        val userProfile = SUT.getUserInfo().first()

        assertEquals("John Doe", userProfile.userName)
        assertEquals("USA", userProfile.country)
        assertEquals("johndoe@example.com", userProfile.email)
        assertEquals("https://www.example.com/profile_image.jpg", userProfile.image)
    }

    @Test(expected = Exception::class)
    fun when_datasource_or_mapping_fails_then_exception_is_thrown() = runTest {
        fakeNetworkDatasource.shouldFail = true
        SUT.getUserInfo().first()
    }

}
