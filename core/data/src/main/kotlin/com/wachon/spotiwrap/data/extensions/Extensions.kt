package com.wachon.spotiwrap.data.extensions

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.OwnerModel
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import com.wachon.spotiwrap.core.network.model.OwnerApi
import com.wachon.spotiwrap.core.network.model.PlaylistApi
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistItemApi
import com.wachon.spotiwrap.core.network.model.UserProfileApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun UserProfileApi.toTrackDB() = UserProfileDB(
    displayName = this.displayName,
    country = this.country,
    email = this.email,
    image = this.images.first().url,
    spotifyId = this.id
)

fun TopItemApi.toTrackDB(index: Int, fame: ItemFame): TrackDB {
    return TrackDB(
        trackId = this.id ?: "",
        trackIndex = index,
        trackFame = fame,
        trackTitle = this.name ?: "",
        trackArtist = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
        trackAlbum = this.album?.name ?: "",
        trackImage = this.album?.images?.first()?.url ?: "",
        trackUri = this.uri ?: ""
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

fun TopItemApi.toTrackModel() = TrackModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    imageUrl = this.album?.images?.first()?.url ?: "",
    title = this.name ?: "",
    artists = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
    album = this.album?.name ?: "",
    uri = this.uri ?: ""
)

fun TopItemApi.toTrackModel(playedAt: String) = TrackModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    imageUrl = this.album?.images?.first()?.url ?: "",
    title = this.name ?: "",
    artists = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
    album = this.album?.name ?: "",
    playedAt = calculateTime(playedAt),
    uri = this.uri ?: ""
)

private fun calculateTime(timestamp: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    try {
        val date = dateFormat.parse(timestamp)
        val now = Date()

        val millisDiff = now.time - date.time
        val minutes = (millisDiff / (1000 * 60)).toInt()
        val hours = minutes / 60

        return if (minutes < 60) {
            "$minutes m"
        } else {
            "$hours h"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return "Error calculating time"
    }
}

fun TopItemApi.toArtistModel() = ArtistModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    name = this.name ?: "",
    imageUrl = this.images?.first()?.url ?: "",
    genres = this.genres ?: emptyList()
)

fun TopPlaylistItemApi.toDomain() =
    this.items?.map { it.track.toTrackModel(it.playedAt) } ?: emptyList()

fun TopPlaylistApi.toDomain() = this.items?.map { it.toDomain() } ?: emptyList()

fun PlaylistApi.toDomain() = PlaylistModel(
    id = this.id ?: "",
    imageUrl = this.images?.firstOrNull()?.url ?: "",
    name = this.name ?: "",
    description = this.description ?: "",
    owner = this.ownerApi?.toDomain() ?: OwnerModel("", "", ""),
    collaborative = this.collaborative ?: false,
    public = this.public ?: false,
    tracks = this.tracks?.items?.map { it.toTrackModel() } ?: emptyList(),
    type = this.type ?: "",
    uri = this.uri ?: "",
)

fun OwnerApi.toDomain() = OwnerModel(
    id = this.id ?: "",
    type = this.type ?: "",
    displayName = this.displayName ?: "",
)