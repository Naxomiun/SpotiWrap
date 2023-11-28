package com.wachon.spotiwrap.data.extensions

import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.OwnerModel
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.common.model.TrackFeaturesModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.database.model.ArtistDB
import com.wachon.spotiwrap.core.database.model.TrackDB
import com.wachon.spotiwrap.core.database.model.UserProfileDB
import com.wachon.spotiwrap.core.network.model.AlbumApi
import com.wachon.spotiwrap.core.network.model.AlbumsItemApi
import com.wachon.spotiwrap.core.network.model.ArtistApi
import com.wachon.spotiwrap.core.network.model.OwnerApi
import com.wachon.spotiwrap.core.network.model.PlaylistApi
import com.wachon.spotiwrap.core.network.model.TopItemApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistApi
import com.wachon.spotiwrap.core.network.model.TopPlaylistItemApi
import com.wachon.spotiwrap.core.network.model.TopRecentlyItemApi
import com.wachon.spotiwrap.core.network.model.TrackFeaturesApi
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
    followers = this.followers.total,
    spotifyId = this.id
)

fun TopItemApi.toTrackDB(index: Int, fame: ItemFame): TrackDB {
    return TrackDB(
        trackId = this.id ?: "",
        trackIndex = index,
        trackFame = fame,
        trackTitle = this.name ?: "",
        trackArtist = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
        trackArtistIds = this.artists?.map { it.id ?: "" } ?: emptyList(),
        trackAlbum = this.album?.name ?: "",
        trackAlbumId = this.album?.id ?: "",
        trackImage = this.album?.images?.first()?.url ?: "",
        trackUri = this.uri ?: "",
        trackDuration = this.durationMS.toString(),
        trackPopularity = this.popularity?.toInt() ?: 0,
        trackExternalUrl = this.externalUrls?.spotify ?: ""
    )
}

fun TopItemApi.toArtistDB(index: Int, fame: ItemFame) = ArtistDB(
    artistId = this.id ?: "",
    artistIndex = index,
    artistFame = fame,
    artistName = this.name ?: "",
    artistImage = this.images?.first()?.url ?: "",
    artistGenres = this.genres ?: emptyList(),
    artistExternalUrl = this.externalUrls?.spotify ?: "",
)

fun TopItemApi.toTrackModel() = TrackModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    imageUrl = this.album?.images?.first()?.url ?: "",
    title = this.name ?: "",
    artists = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
    artistsIds = this.artists?.map { it.id ?: "" } ?: emptyList(),
    album = this.album?.name ?: "",
    albumId = this.album?.id ?: "",
    uri = this.uri ?: "",
    duration = getDurationFormat(this.durationMS?.toInt() ?: 0),
    popularity = this.popularity?.toInt() ?: 0,
    externalUrl = this.externalUrls?.spotify ?: ""
)

fun TrackFeaturesApi.toTrackFeatureModel() = TrackFeaturesModel(
    acousticness = this.acousticness,
    danceability = this.danceability,
    energy = this.energy,
    instrumentalness = this.instrumentalness,
    liveness = this.liveness,
    speechiness = this.speechiness,
)

fun TopItemApi.toTrackModel(playedAt: String = "") = TrackModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    imageUrl = this.album?.images?.first()?.url ?: "",
    title = this.name ?: "",
    artists = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
    artistsIds = this.artists?.map { it.id ?: "" } ?: emptyList(),
    album = this.album?.name ?: "",
    albumId = this.album?.id ?: "",
    playedAt = calculateTime(playedAt),
    uri = this.uri ?: "",
    duration = getDurationFormat(this.durationMS?.toInt() ?: 0),
    popularity = this.popularity?.toInt() ?: 0,
    externalUrl = this.externalUrls?.spotify ?: ""
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
    genres = this.genres ?: emptyList(),
    externalUrl = this.externalUrls?.spotify ?: ""
)

fun AlbumsItemApi.toTrackModel() = TrackModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    imageUrl = this.images?.first()?.url ?: "",
    title = this.name ?: "",
    artists = this.artists?.joinToString(", ") { it.name ?: "" } ?: "",
    artistsIds = this.artists?.map { it.id ?: "" } ?: emptyList(),
    album = "",
    albumId = "",
    uri = this.uri ?: "",
    duration = getDurationFormat(this.durationMS?.toInt() ?: 0),
    popularity = 0,
    externalUrl = this.externalUrls?.spotify ?: ""
)

fun ArtistApi.toArtistModel() = ArtistModel(
    id = this.id ?: "",
    fame = ItemFame.NONE,
    name = this.name ?: "",
    imageUrl = this.images?.first()?.url ?: "",
    genres = this.genres ?: emptyList(),
    followers = this.followers?.total ?: 0,
    popularity = this.popularity ?: 0,
    externalUrl = this.externalUrls?.spotify ?: ""
)

fun TopPlaylistItemApi.toDomain() =
    this.items?.map { it.track.toTrackModel() } ?: emptyList()

fun TopRecentlyItemApi.toDomain() =
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

fun AlbumApi.toDomain() = AlbumModel(
    id = this.id ?: "",
    name = this.name ?: "",
    totalTracks = this.totalTracks?.toInt() ?: 0,
    artists = this.artistApis?.map { it.toArtistModel() } ?: emptyList(),
    imageUrl = this.images?.first()?.url ?: "",
    releaseDate = this.releaseDate ?: "",
    releaseDatePrecision = this.releaseDatePrecision ?: "",
    tracks = this.tracks?.items?.map { it.toTrackModel() } ?: emptyList(),
    genres = this.genres ?: emptyList(),
    label = this.label ?: "",
    popularity = this.popularity?.toInt() ?: 0,
    externalUrl = this.externalUrls?.spotify ?: "",
    albumDuration = getDuration(this.tracks?.items?.map { it.durationMS?.toInt() ?: 0 }
        ?: emptyList())
)

private fun getDuration(tracks: List<Int>): String {
    val totalSum = tracks.sumOf { it }
    return getDurationFormat(totalSum)
}

private fun getDurationFormat(duration: Int): String {
    val seconds = duration / 1000
    val minutes = seconds / 60
    val hours = minutes / 60

    val secondsRemaining = seconds % 60
    val minutesRemaining = minutes % 60

    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutesRemaining, secondsRemaining)
    } else {
        String.format("%02d:%02d", minutesRemaining, secondsRemaining)
    }
}