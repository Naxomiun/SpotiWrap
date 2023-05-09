package com.wachon.spotiwrap.core.database.datasource

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import kotlinx.coroutines.flow.Flow

@Database(entities = [UserProfileDB::class, TrackDB::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun trackDao(): TrackDao
}

@Dao
interface ProfileDao {
    @Insert
    fun insertProfile(profileDB: UserProfileDB)

    @Query("SELECT * FROM profile")
    fun getProfile(): Flow<UserProfileDB>
}

@Dao
interface TrackDao {
    @Insert
    fun insertTracks(tracksDB: List<TrackDB>)

    @Query("SELECT * FROM tracks")
    fun getTracks(): Flow<List<TrackDB>>
}
