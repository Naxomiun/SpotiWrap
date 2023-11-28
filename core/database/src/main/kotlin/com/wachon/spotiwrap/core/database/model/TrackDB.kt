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
    val trackArtistIds: List<String>,
    val trackAlbum: String,
    val trackAlbumId: String,
    val trackImage: String,
    val trackUri: String,
    val trackDuration: String,
    val trackPopularity: Int,
    val trackExternalUrl: String,
) {
    fun toDomain(): TrackModel = TrackModel(
        id = this.trackId,
        fame = this.trackFame,
        imageUrl = this.trackImage,
        title = this.trackTitle,
        artists = this.trackArtist,
        artistsIds = this.trackArtistIds,
        album = this.trackAlbum,
        albumId = this.trackAlbumId,
        uri = this.trackUri,
        duration = this.trackDuration,
        popularity = this.trackPopularity,
        externalUrl = this.trackExternalUrl,
    )
}