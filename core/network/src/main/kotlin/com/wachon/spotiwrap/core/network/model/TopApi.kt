package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopApi(
    val href: String?,
    val limit: Long?,
    val next: String?,
    val offset: Long?,
    //val previous: Top? = null,
    val total: Long?,
    val items: List<TopItemApi>?
)

@Serializable
data class TopPlaylistApi(
    val href: String?,
    val limit: Long?,
    val next: String?,
    val offset: Long?,
    //val previous: Top? = null,
    val total: Long?,
    val items: List<PlaylistApi>?
)

@Serializable
data class TopPlaylistItemApi(
    val href: String?,
    val limit: Long?,
    val next: String?,
    val offset: Long?,
    //val previous: Top? = null,
    val total: Long?,
    val items: List<PlaylistItemApi>?
)

@Serializable
data class TopRecentlyItemApi(
    val href: String?,
    val limit: Long?,
    val next: String?,
    val offset: Long?,
    //val previous: Top? = null,
    val total: Long?,
    val items: List<RecentlyItemApi>?
)

@Serializable
data class TopItemApi(
    @SerialName("album")
    val album: AlbumApi?,
    @SerialName("artists")
    val artists: List<ArtistApi>?,
    @SerialName("available_markets")
    val availableMarkets: List<String>?,
    @SerialName("disc_number")
    val discNumber: Long?,
    @SerialName("duration_ms")
    val durationMS: Long?,
    @SerialName("explicit")
    val explicit: Boolean?,
    @SerialName("external_ids")
    val externalIds: ExternalIdsApi?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("popularity")
    val popularity: Long?,
    @SerialName("preview_url")
    val previewURL: String?,
    @SerialName("track_number")
    val trackNumber: Long?,
    @SerialName("type")
    val type: String?,
    @SerialName("uri")
    val uri: String?,
    @SerialName("is_local")
    val isLocal: Boolean?,
    @SerialName("images")
    val images: List<ImageApi>?,
    @SerialName("genres")
    val genres: List<String>?
)

@Serializable
data class AlbumApi(
    @SerialName("album_type")
    val albumType: String?,
    @SerialName("total_tracks")
    val totalTracks: Long?,
    @SerialName("available_markets")
    val availableMarkets: List<String>?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
    val href: String?,
    val id: String?,
    val images: List<ImageApi>?,
    val name: String?,
    @SerialName("release_date")
    val releaseDate: String?,
    @SerialName("release_date_precision")
    val releaseDatePrecision: String?,
    val type: String?,
    val uri: String?,
    val artistApis: List<ArtistApi>?
)

@Serializable
data class ArtistApi(
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
    val href: String?,
    val id: String?,
    val name: String?,
    val type: String?,
    val uri: String?,
    val images: List<ImageApi>?,
    val genres: List<String>?
)

@Serializable
data class ExternalIdsApi(
    val isrc: String?
)