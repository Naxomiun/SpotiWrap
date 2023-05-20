package com.wachon.spotiwrap.features.profile.presentation.profilebar

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.CurrentTrackModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.features.profile.presentation.model.CurrentTrackUI
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI

@Immutable
data class ProfileTopBarState(
    val userUI: UserUI? = null,
    val currentTrackUI: CurrentTrackUI? = null
) {

    fun isLoading(): Boolean {
        return userUI == null
    }

    fun isCurrentSongPlaying(): Boolean {
        return currentTrackUI != null
    }

}