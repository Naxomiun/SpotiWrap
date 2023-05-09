package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackDB(
    @PrimaryKey val id: Int,
    val trackTitle: String,
    val trackArtist: String,
    val trackImage: String
)