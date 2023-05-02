package com.wachon.spotiwrap.core.network.model

import com.wachon.spotiwrap.core.common.model.UserProfileModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileApi(
    @SerialName("country")
    val country: String?,
    @SerialName("display_name")
    val displayName: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("explicit_content")
    val explicitContent: ExplicitContentApi?,
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
    @SerialName("product")
    val product: String?,
    @SerialName("type")
    val type: String?,
    @SerialName("uri")
    val uri: String?
) {

    fun toDomain(): UserProfileModel {
        return UserProfileModel(
            country = this.country ?: "",
            userName = this.displayName ?: "",
            email = this.email ?: "",
            image = this.images?.first()?.url ?: ""
        )
    }

}

@Serializable
data class ExplicitContentApi(
    @SerialName("filter_enabled")
    val filterEnabled: Boolean?,
    @SerialName("filter_locked")
    val filterLocked: Boolean?
)

@Serializable
data class FollowersApi(
    val href: String?,
    val total: Long?
)
