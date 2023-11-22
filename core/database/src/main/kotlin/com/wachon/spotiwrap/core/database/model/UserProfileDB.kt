package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wachon.spotiwrap.core.common.model.CurrentTrackModel
import com.wachon.spotiwrap.core.common.model.UserProfileModel

@Entity(tableName = "profile")
data class UserProfileDB(
    @PrimaryKey val email: String,
    val displayName: String,
    val country: String,
    val image: String,
    val followers: Int,
    val spotifyId: String,
) {
    fun toDomain(currentSong: CurrentTrackModel?): UserProfileModel = UserProfileModel(
        displayName = this.displayName,
        country = this.country,
        email = this.email,
        image = this.image,
        spotifyId = this.spotifyId,
        currentSong = currentSong,
        followers = this.followers
    )
}
