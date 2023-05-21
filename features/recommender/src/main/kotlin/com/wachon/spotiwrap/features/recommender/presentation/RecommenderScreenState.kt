package com.wachon.spotiwrap.features.recommender.presentation

import androidx.compose.runtime.Immutable
import okhttp3.internal.immutableListOf

@Immutable
data class RecommenderScreenState(
    val genres: List<String> = immutableListOf()
)