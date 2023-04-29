package com.wachon.spotiwrap.features.menu.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Top(
    val href: String?,
    val limit: Long?,
    val next: String?,
    val offset: Long?,
    //val previous: Top? = null,
    val total: Long?,
    val items: List<Item>?
)

@Serializable
data class Item(
    val album: Album?,
    val artists: List<Artist>?,

    @SerialName("available_markets")
    val availableMarkets: List<String>?,

    @SerialName("disc_number")
    val discNumber: Long?,

    @SerialName("duration_ms")
    val durationMS: Long?,

    val explicit: Boolean?,

    @SerialName("external_ids")
    val externalIDS: ExternalIDS?,

    @SerialName("external_urls")
    val externalUrls: ExternalUrls?,

    val href: String?,
    val id: String?,
    val name: String?,
    val popularity: Long?,

    @SerialName("preview_url")
    val previewURL: String?,

    @SerialName("track_number")
    val trackNumber: Long?,

    val type: String?,
    val uri: String?,

    @SerialName("is_local")
    val isLocal: Boolean?
) {

    fun getArtistToShow(): String {
        return this.artists?.joinToString(separator = ", ") { it.name ?: "" } ?: ""
    }

}

@Serializable
data class Album(
    @SerialName("album_type")
    val albumType: String?,

    @SerialName("total_tracks")
    val totalTracks: Long?,

    @SerialName("available_markets")
    val availableMarkets: List<String>?,

    @SerialName("external_urls")
    val externalUrls: ExternalUrls?,

    val href: String?,
    val id: String?,
    val images: List<Image>?,
    val name: String?,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("release_date_precision")
    val releaseDatePrecision: String?,

    val type: String?,
    val uri: String?,
    val artists: List<Artist>?
)

@Serializable
data class Artist(
    @SerialName("external_urls")
    val externalUrls: ExternalUrls?,

    val href: String?,
    val id: String?,
    val name: String?,
    val type: String?,
    val uri: String?
)

@Serializable
data class ExternalIDS(
    val isrc: String?
)