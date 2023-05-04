package com.wachon.spotiwrap.features.profile.presentation.model

import android.util.Log
import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.UserProfileModel

@Immutable
data class UserUI(
    val displayName: String,
    val country: String,
    val email: String,
    val image: String
)

fun UserProfileModel.toUI(): UserUI {
    return UserUI(
        displayName = this.displayName,
        country = this.country,
        email = this.email,
        image = this.image
    )
}