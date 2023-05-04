package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    getUserProfile: GetUserProfileUseCase,
    getUserTopArtists: GetUserTopArtistsUseCase,
    getUserTopTracks: GetUserTopTracksUseCase
) : ViewModel() {

    private val topTracks = getUserTopTracks(limit = 10)
        .map { trackList ->
            trackList
                .map { it.toUI() }
                .toImmutableList()
        }
    //private val topArtists = getUserTopArtists(limit = 10)
    private val userProfile = getUserProfile()
        .map { it.toUI() }


    val uiState = combine(
        userProfile,
        topTracks
    ) { userProfile, topTracks ->
        MenuScreenState(
            loading = false,
            userProfile = userProfile,
            topTracks = topTracks
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MenuScreenState()
    )


    private fun UserProfileModel.toUI(): UserUI {
        return UserUI(
            displayName = this.displayName,
            country = this.country,
            email = this.email,
            image = this.image
        )
    }

    private fun TrackModel.toUI(): TrackUI {
        return TrackUI(
            trackTitle = title,
            trackImage = imageUrl,
            trackArtist = artists.joinToString(",") { it.name }
        )
    }

}