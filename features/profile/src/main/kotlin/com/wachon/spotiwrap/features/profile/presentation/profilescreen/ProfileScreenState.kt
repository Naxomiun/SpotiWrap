package com.wachon.spotiwrap.features.profile.presentation.profilescreen

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class ProfileScreenState(
    val userProfile: UserUI? = null,
    val userPlaylists: ImmutableList<PlaylistModel> = persistentListOf()
)