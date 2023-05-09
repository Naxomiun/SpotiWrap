package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wachon.spotiwrap.core.common.model.UserProfileModel

@Entity(tableName = "profile")
data class UserProfileDB(
    @PrimaryKey val email: String,
    val displayName: String,
    val country: String,
    val image: String,
    //TODO add time stamp
) {
    fun toDomain(): UserProfileModel = UserProfileModel(
        displayName = this.displayName, country = this.country, email = this.email, image = this.image
    )
}
