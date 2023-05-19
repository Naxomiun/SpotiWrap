package com.wachon.spotiwrap.core.database.datasource

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.wachon.spotiwrap.core.database.converter.ListStringConverter
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import kotlinx.coroutines.flow.Flow

@Database(
    entities = [
        UserProfileDB::class,
        TrackDB::class,
        ArtistDB::class
    ],
    version = 2
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun trackDao(): TrackDao
    abstract fun artistDao(): ArtistDao
}

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profileDB: UserProfileDB)

    @Query("SELECT * FROM profile")
    fun getProfileNoFLow(): UserProfileDB

    @Query("SELECT * FROM profile")
    fun getProfile(): Flow<UserProfileDB>
}

@Dao
interface TrackDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTracks(tracksDB: List<TrackDB>)

    @Query("SELECT * FROM tracks")
    fun getTracksNoFlow(): List<TrackDB>

    @Query("SELECT * FROM tracks")
    fun getTracks(): Flow<List<TrackDB>>
}

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtists(artistDB: List<ArtistDB>)

    @Query("SELECT * FROM artists")
    fun getArtistsNoFlow(): List<ArtistDB>

    @Query("SELECT * FROM artists")
    fun getArtists(): Flow<List<ArtistDB>>
}
