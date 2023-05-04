package com.wachon.spotiwrap.features.profile.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class UserUI(
    val displayName: String,
    val country: String,
    val email: String,
    val image: String
)