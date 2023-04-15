package com.wachon.spotiwrap.features.menu.data

import com.google.gson.annotations.SerializedName

data class Top(
    val href: String,
    val limit: Long,
    val next: String,
    val offset: Long,
    val previous: Any? = null,
    val total: Long,
    val items: List<Item>
)

data class Item(
    val album: Album,
    val artists: List<Artist>,

    @SerializedName("available_markets")
    val availableMarkets: List<String>,

    @SerializedName("disc_number")
    val discNumber: Long,

    @SerializedName("duration_ms")
    val durationMS: Long,

    val explicit: Boolean,

    @SerializedName("external_ids")
    val externalIDS: ExternalIDS,

    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,

    val href: String,
    val id: String,
    val name: String,
    val popularity: Long,

    @SerializedName("preview_url")
    val previewURL: String,

    @SerializedName("track_number")
    val trackNumber: Long,

    val type: String,
    val uri: String,

    @SerializedName("is_local")
    val isLocal: Boolean
) {

    fun getArtistToShow(): String {
        return this.artists.joinToString(separator = ", ") { it.name }
    }

}

data class Album(
    @SerializedName("album_type")
    val albumType: String,

    @SerializedName("total_tracks")
    val totalTracks: Long,

    @SerializedName("available_markets")
    val availableMarkets: List<String>,

    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,

    val href: String,
    val id: String,
    val images: List<Image>,
    val name: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("release_date_precision")
    val releaseDatePrecision: String,

    val type: String,
    val uri: String,
    val artists: List<Artist>
)

data class Artist(
    @SerializedName("external_urls")
    val externalUrls: ExternalUrls,

    val href: String,
    val id: String,
    val name: String,
    val type: String,
    val uri: String
)

data class ExternalIDS(
    val isrc: String
)