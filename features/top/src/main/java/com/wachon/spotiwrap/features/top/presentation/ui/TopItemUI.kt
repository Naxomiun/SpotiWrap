package com.wachon.spotiwrap.features.top.presentation.ui

import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel

data class TopItemUI(
    val id: String,
    val image: String,
    val first: String,
    val second: String = "",
)

fun TrackModel.toUI() = TopItemUI(
    id = this.id,
    image = this.imageUrl,
    first = this.title,
    second = this.artists
)

fun ArtistModel.toUI() = TopItemUI(
    id = this.id,
    image = this.imageUrl,
    first = this.name
)