package com.wachon.spotiwrap.features.home.domain

import androidx.compose.runtime.Immutable

@Immutable
data class TopGenreUI(
    val genreName: String,
    val genreChartValue: Float
)