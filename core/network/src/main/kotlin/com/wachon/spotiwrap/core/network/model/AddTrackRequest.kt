package com.wachon.spotiwrap.core.network.model

import kotlinx.serialization.Serializable

@Serializable
class AddTrackRequest(
    val uris: List<String>,
    val position: Int
)