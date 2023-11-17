package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaylistApi(
    @SerialName("collaborative")
    val collaborative: Boolean?,
    @SerialName("description")
    val description: String?,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
    @SerialName("followers")
    val followers: FollowersApi?,
    @SerialName("href")
    val href: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("images")
    val images: List<ImageApi>?,
    @SerialName("name")
    val name: String?,
    @SerialName("owner")
    val ownerApi: OwnerApi?,
    @SerialName("public")
    val public: Boolean?,
    @SerialName("snapshot_id")
    val snapshotId: String?,
    @SerialName("tracks")
    val tracks: TopApi?,
    @SerialName("type")
    val type: String?,
    @SerialName("uri")
    val uri: String?
)

@Serializable
data class OwnerApi(
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi?,
    val followers: FollowersApi?,
    val href: String?,
    val id: String?,
    val type: String?,
    val uri: String?,
    @SerialName("display_name")
    val displayName: String?,
)