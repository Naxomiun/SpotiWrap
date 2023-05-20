package com.wachon.spotiwrap.features.profile.presentation.model

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.CurrentTrackModel
import com.wachon.spotiwrap.core.common.model.UserProfileModel

@Immutable
data class UserUI(
    val displayName: String,
    val country: String,
    val email: String,
    val image: String,
    val currentTrackUI: CurrentTrackUI?
) {
    fun isCurrentSongPlaying(): Boolean {
        return currentTrackUI != null
    }
}

@Immutable
data class CurrentTrackUI(
    val title: String,
    val image: String,
    val artist: String
)

fun UserProfileModel.toUI(): UserUI {
    return UserUI(
        displayName = this.displayName,
        country = this.country,
        email = this.email,
        image = this.image,
        currentTrackUI = currentSong.toUI()
    )
}

fun CurrentTrackModel?.toUI(): CurrentTrackUI? {
    return if(this != null)
        CurrentTrackUI(
            title = this.title,
            image = this.imageUrl,
            artist = this.artist
        )
    else
        null
}