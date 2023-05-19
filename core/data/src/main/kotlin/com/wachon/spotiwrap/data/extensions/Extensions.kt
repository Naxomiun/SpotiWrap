package com.wachon.spotiwrap.data.extensions

import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi

fun UserProfileApi.toTrackDB() = UserProfileDB(
    displayName = this.displayName,
    country = this.country,
    email = this.email,
    image = this.images.first().url
)

fun TopItemApi.toTrackDB(index: Int, fame: ItemFame): TrackDB {
    return TrackDB(
        trackId = this.id ?: "",
        trackIndex = index,
        trackFame = fame,
        trackTitle = this.name ?: "",
        trackArtist = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
        trackImage = this.album?.images?.first()?.url ?: "",
    )
}

fun TopItemApi.toArtistDB(index: Int, fame: ItemFame) = ArtistDB(
    artistId = this.id ?: "",
    artistIndex = index,
    artistFame = fame,
    artistName = this.name ?: "",
    artistImage = this.images?.first()?.url ?: "",
    artistGenres = this.genres ?: emptyList()
)
