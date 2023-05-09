package com.wachon.spotiwrap.features.menu.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.model.toUI
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    dispatcherProvider: DispatcherProvider,
    getUserProfile: GetUserProfileUseCase,
    getUserTopArtists: GetUserTopArtistsUseCase,
    getUserTopTracks: GetUserTopTracksUseCase,
) : ViewModel() {

    private val topTracks = getUserTopTracks(limit = 10)
        .map { it.toUI() }
    private val topArtists = getUserTopArtists(limit = 10)
        .map { it.toUI() }
    private val userProfile = getUserProfile()
        .map { it.toUI() }

    val uiState = combine(
        userProfile,
        topTracks,
        topArtists
    ) { userProfile, topTracks, topArtists ->
        MenuScreenState(
            loading = false,
            userProfile = userProfile,
            topTracks = topTracks,
            topArtists = topArtists
        )
    }   .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = MenuScreenState()
        )

}