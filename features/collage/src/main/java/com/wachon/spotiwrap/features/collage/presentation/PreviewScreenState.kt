package com.wachon.spotiwrap.features.collage.presentation

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.LONG_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.MEDIUM_TERM
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange.SHORT_TERM
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum.COLLAGE
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum.TOP
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.BIG
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.MEDIUM
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum.SMALL
import com.wachon.spotiwrap.features.collage.presentation.CollageTypesEnum.ARTISTS
import com.wachon.spotiwrap.features.collage.presentation.CollageTypesEnum.TRACKS

@Immutable
data class PreviewScreenState(
    val isLoading: Boolean = true,

    val previewBitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888),

    val timeIndex: Int = 0,
    val time: List<TopItemTimeRange> = listOf(SHORT_TERM, MEDIUM_TERM, LONG_TERM),
    val optionIndex: Int = 0,
    val options: List<CollageOptionsEnum> = listOf(TOP, COLLAGE),
    val typeIndex: Int = 0,
    val types: List<CollageTypesEnum> = listOf(ARTISTS, TRACKS),
    val sizeIndex: Int = 0,
    val sizes: List<CollageSizesEnum> = listOf(SMALL, MEDIUM, BIG),

    val artists: List<ArtistModel> = listOf(),
    val artistsCovers: List<String> = listOf(),
    val albums: List<TrackModel> = listOf(),
    val albumsCovers: List<String> = listOf(),

    )