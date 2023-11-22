package com.wachon.spotiwrap.features.collage.presentation.components

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum
import com.wachon.spotiwrap.features.collage.presentation.CollageTypesEnum

@Composable
fun CollageScreen(
    artistsCovers: List<String>,
    albumsCovers: List<String>,
    typeIndex: Int,
    sizeIndex: Int,
    onBitmapCreated: (Bitmap) -> Unit
) {
    val selectedCovers = if (typeIndex == CollageTypesEnum.ARTISTS.ordinal) {
        artistsCovers
    } else {
        albumsCovers
    }
    when (sizeIndex) {
        CollageSizesEnum.SMALL.ordinal -> {
            ThreeColumnCollage(
                covers = selectedCovers,
                onBitmapCreated = onBitmapCreated
            )
        }

        CollageSizesEnum.MEDIUM.ordinal -> {
            FourColumnCollage(
                covers = selectedCovers,
                onBitmapCreated = onBitmapCreated

            )
        }

        CollageSizesEnum.BIG.ordinal -> {
            FiveColumnCollage(
                covers = selectedCovers,
                onBitmapCreated = onBitmapCreated
            )
        }
    }
}