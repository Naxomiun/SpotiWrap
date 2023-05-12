package com.wachon.spotiwrap.features.home.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import com.wachon.spotiwrap.features.home.domain.GetUserTopGenresFromArtistsUseCase
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.model.toUI
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.collections.immutable.toImmutableList
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
    getUserTopGenres: GetUserTopGenresFromArtistsUseCase
) : ViewModel() {

    private val userProfile = getUserProfile()
        .map { it.toUI() }

    private val topTracks = getUserTopTracks(limit = 10)
        .map { it.toUI() }

    private val topArtists = getUserTopArtists(limit = 10)

    private val topGenres = getUserTopGenres(topArtists)

    val uiState = combine(
        userProfile,
        topTracks,
        topArtists,
        topGenres
    ) { userProfile, topTracks, topArtists, topGenres ->

        Log.e("CACA", topGenres.toString())

        HomeScreenState(
            loading = false,
            userProfile = userProfile,
            topTracks = topTracks,
            topArtists = topArtists.toUI(),
            topGenres = topGenres.toImmutableList()
        )
    }   .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeScreenState()
        )

}