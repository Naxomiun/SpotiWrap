package com.wachon.spotiwrap.features.menu.data

import kotlinx.serialization.Serializable

@Serializable
data class ExternalUrls(
    val spotify: String
)

@Serializable
data class Image(
    val url: String,
    val height: Long?,
    val width: Long?
)