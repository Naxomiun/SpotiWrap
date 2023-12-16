package com.wachon.spotiwrap.features.collage.presentation

import android.graphics.Bitmap
import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.features.artists.domain.TopGenreUI

@Immutable
data class PreviewScreenState(
    val isLoading: Boolean = true,

    val previewBitmap: Bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888),

    val artistsShort: List<ArtistModel> = listOf(),
    val artistsMedium: List<ArtistModel> = listOf(),
    val artistsLong: List<ArtistModel> = listOf(),

    val tracksShort: List<TrackModel> = listOf(),
    val tracksMedium: List<TrackModel> = listOf(),
    val tracksLong: List<TrackModel> = listOf(),

    val genresShort: List<TopGenreUI> = listOf(),
    val genresMedium: List<TopGenreUI> = listOf(),
    val genresLong: List<TopGenreUI> = listOf(),

    )