package com.wachon.spotiwrap.features.menu.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val country: String,
    @SerialName("display_name")
    val displayName: String,
    val email: String,
    @SerialName("explicit_content")
    val explicitContent: ExplicitContent,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: List<Image>,
    val product: String,
    val type: String,
    val uri: String
)

@Serializable
data class ExplicitContent(
    @SerialName("filter_enabled")
    val filterEnabled: Boolean,
    @SerialName("filter_locked")
    val filterLocked: Boolean
)

@Serializable
data class Followers(
    val href: String?,
    val total: Long
)
