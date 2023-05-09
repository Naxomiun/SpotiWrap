package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.runtime.Immutable
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
data class MenuScreenState(
    val loading: Boolean = true,
    val userProfile: UserUI? = null,
    val topTracks: ImmutableList<TrackUI> = persistentListOf(),
    val topArtists: ImmutableList<ArtistUI> = persistentListOf()
)