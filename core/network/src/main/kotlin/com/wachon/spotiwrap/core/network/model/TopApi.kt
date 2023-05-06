package com.wachon.spotiwrap.core.network.model

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
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
data class TopItemApi(
    val album: AlbumApi?,
    val artists: List<ArtistApi>?,
    @SerialName("available_markets")
    val availableMarkets: List<String>?,
    @SerialName("disc_number")
    val discNumber: Long?,
    @SerialName("duration_ms")
    val durationMS: Long?,
    val explicit: Boolean?,
    @SerialName("external_ids")
    val externalIds: ExternalIdsApi?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
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
    val isLocal: Boolean?,
    val images: List<ImageApi>?
) {

    fun toTrackModel(): TrackModel {
        return TrackModel(
            id = this.id ?: "",
            imageUrl = this.album?.images?.first()?.url ?: "",
            title = this.name ?: "",
            artists = this.artists?.map { it.toDomain() } ?: emptyList()
        )
    }

    fun toArtistModel(): ArtistModel {
        return ArtistModel(
            id = this.id ?: "",
            imageUrl = this.images?.first()?.url ?: "",
            name = this.name ?: "",
        )
    }

}

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
    val images: List<ImageApi>?
) {

    fun toDomain(): ArtistModel {
        return ArtistModel (
            id = this.id ?: "",
            name = this.name ?: "",
            imageUrl = this.images?.first()?.url ?: ""
        )
    }

}

@Serializable
data class ExternalIdsApi(
    val isrc: String?
)