package com.wachon.spotiwrap.core.network.model

import com.wachon.spotiwrap.core.common.model.UserModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApi(
    val country: String,
    @SerialName("display_name")
    val displayName: String?,
    val email: String,
    @SerialName("explicit_content")
    val explicitContent: ExplicitContent,
    @SerialName("external_urls")
    val externalUrls: ExternalUrlsApi,
    val followers: Followers,
    val href: String,
    val id: String,
    val images: List<ImageApi>,
    val product: String,
    val type: String,
    val uri: String
) {

    fun toDomain(): UserModel {
        return UserModel(
            country = this.country,
            userName = this.displayName ?: "",
            email = this.email,
            image = this.images.first().url
        )
    }

}

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
