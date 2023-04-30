package com.wachon.spotiwrap.features.menu.data.model

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