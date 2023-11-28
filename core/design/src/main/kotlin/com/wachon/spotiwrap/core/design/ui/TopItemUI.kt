package com.wachon.spotiwrap.core.design.ui

import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel

data class TopItemUI(
    val id: String,
    val image: String,
    val first: String,
    val second: String = "",
    val third: String = "",
)

fun TrackModel.toItemUI() = TopItemUI(
    id = this.id,
    image = this.imageUrl,
    first = this.title,
    second = this.artists,
    third = this.duration
)

fun ArtistModel.toItemUI() = TopItemUI(
    id = this.id,
    image = this.imageUrl,
    first = this.name
)

fun AlbumModel.toItemUI() = TopItemUI(
    id = this.id,
    image = this.imageUrl,
    first = this.name,
    third = this.albumDuration
)