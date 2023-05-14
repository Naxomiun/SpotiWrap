package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "extUrl")
data class ExternalUrlsDB(
    @PrimaryKey val spotify: String
)

@Entity(tableName = "extId")
data class ExternalIdsDB(
    @PrimaryKey val isrc: String
)

@Entity(tableName = "image")
data class ImageDB(
    @PrimaryKey val url: String,
    val height: Long,
    val width: Long
)