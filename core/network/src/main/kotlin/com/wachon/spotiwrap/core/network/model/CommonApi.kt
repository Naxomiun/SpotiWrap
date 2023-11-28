package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrlsApi(
    val spotify: String
)

@Serializable
data class ImageApi(
    val url: String,
    val height: Long?,
    val width: Long?
)

@Serializable
data class RestrictionsApi(
    val reason: String,
)