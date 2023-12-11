package com.wachon.spotiwrap.features.collage.presentation.components

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum

@Composable
fun TopPreview(
    timeIndex: Int,
    time: List<TopItemTimeRange>,
    artists: List<ArtistModel>,
    artistsCovers: List<String>,
    albums: List<TrackModel>,
    albumsCovers: List<String>,
    optionIndex: Int,
    typeIndex: Int,
    sizeIndex: Int,
    onBitmapCreated: (Bitmap) -> Unit
) {
    when (optionIndex) {
        CollageOptionsEnum.TOP.ordinal -> {
            TopScreenContent(
                time = time[timeIndex],
                artists = artists,
                albums = albums,
                onBitmapCreated = onBitmapCreated
            )
        }

        CollageOptionsEnum.COLLAGE.ordinal -> {
            CollageScreen(
                artistsCovers = artistsCovers,
                albumsCovers = albumsCovers,
                typeIndex = typeIndex,
                sizeIndex = sizeIndex,
                onBitmapCreated = onBitmapCreated
            )
        }
    }
}

