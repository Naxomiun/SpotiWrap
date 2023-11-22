package com.wachon.spotiwrap.features.collage.presentation.components

import android.graphics.Bitmap
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.BIG
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.MEDIUM
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.SMALL
import com.wachon.spotiwrap.features.collage.presentation.CollageTypesEnum
import kotlin.math.pow

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
        SMALL.ordinal -> {
            ColumnCollage(
                columns = SMALL.value,
                covers = selectedCovers.take(SMALL.value.toDouble().pow(2.toDouble()).toInt()),
                onBitmapCreated = onBitmapCreated
            )
        }

        MEDIUM.ordinal -> {
            ColumnCollage(
                columns = MEDIUM.value,
                covers = selectedCovers.take(
                    MEDIUM.value.toDouble().pow(MEDIUM.value.toDouble()).toInt()
                ),
                onBitmapCreated = onBitmapCreated
            )
        }

        BIG.ordinal -> {
            ColumnCollage(
                columns = BIG.value,
                covers = selectedCovers.take(
                    BIG.value.toDouble().pow(BIG.value.toDouble()).toInt()
                ),
                onBitmapCreated = onBitmapCreated
            )
        }
    }
}