package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TrackModel

@Entity(tableName = "tracks")
data class TrackDB(
    @PrimaryKey val trackId: String,
    val trackIndex: Int,
    val trackFame: ItemFame = ItemFame.NONE,
    val trackTitle: String,
    val trackArtist: String,
    val trackAlbum: String,
    val trackImage: String,
    val trackUri: String,
) {
    fun toDomain(): TrackModel = TrackModel(
        id = this.trackId,
        fame = this.trackFame,
        imageUrl = this.trackImage,
        title = this.trackTitle,
        artists = this.trackArtist,
        album = this.trackAlbum,
        uri = this.trackUri
    )
}