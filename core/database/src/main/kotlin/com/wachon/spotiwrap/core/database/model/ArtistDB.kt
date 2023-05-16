package com.wachon.spotiwrap.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame

@Entity(tableName = "artists")
data class ArtistDB(
    @PrimaryKey val artistId: String,
    val artistIndex: Int,
    val artistFame: ItemFame = ItemFame.NONE,
    val artistName: String,
    val artistImage: String
) {
    fun toDomain(): ArtistModel = ArtistModel(
        id = this.artistId,
        fame = this.artistFame,
        imageUrl = this.artistImage,
        name = this.artistName
    )
}