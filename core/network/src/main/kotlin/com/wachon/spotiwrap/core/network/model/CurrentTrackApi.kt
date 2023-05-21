package com.wachon.spotiwrap.core.network.model

import com.wachon.spotiwrap.core.common.model.CurrentTrackModel
import kotlinx.serialization.Serializable

@Serializable
data class CurrentTrackApi(
    val item: CurrentItemApi?
) {

    fun toDomain(): CurrentTrackModel? {
        return item?.toDomain()
    }

}


@Serializable
data class CurrentItemApi(
    val name: String,
    val type: String,
    val album: AlbumApi?,
    val artists: List<ArtistApi>,
) {

    fun toDomain(): CurrentTrackModel {
        return CurrentTrackModel(
            title = name,
            artist = this.artists.joinToString(", ") { it.name ?: "" },
            imageUrl = this.album?.images?.firstOrNull()?.url ?: ""
        )
    }

}