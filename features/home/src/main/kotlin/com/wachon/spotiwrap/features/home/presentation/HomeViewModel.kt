package com.wachon.spotiwrap.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.artists.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.artists.domain.GetUserTopGenresFromArtistsUseCase
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.model.toUI
import com.wachon.spotiwrap.features.recently.domain.GetRecentlyPlayedUseCase
import com.wachon.spotiwrap.features.tracks.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.tracks.presentation.model.toUI
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    dispatcherProvider: DispatcherProvider,
    getUserProfile: GetUserProfileUseCase,
    getUserTopArtists: GetUserTopArtistsUseCase,
    getUserTopTracks: GetUserTopTracksUseCase,
    getUserTopGenres: GetUserTopGenresFromArtistsUseCase,
    getRecentlyPlayed: GetRecentlyPlayedUseCase,
) : ViewModel() {

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    private val userProfile = getUserProfile()
        .map { it?.toUI() }

    private val topTracks = getUserTopTracks(limit = 10)
        .map { it.toUI() }

    private val topArtists = getUserTopArtists(limit = 10)

    private val topGenres = getUserTopGenres(topArtists)

    private val recentlyPlayed = getRecentlyPlayed()

    init {
        launchSyncWorker()
    }

    val uiState = combine(
        userProfile,
        topTracks,
        topArtists,
        topGenres,
        recentlyPlayed,
    ) { userProfile, topTracks, topArtists, topGenres, recentlyPlayed ->

        val isLoading =
            userProfile == null ||
                    topTracks.isEmpty() ||
                    topArtists.isEmpty() ||
                    recentlyPlayed.isEmpty()

        HomeScreenState(
            loading = isLoading,
            userProfile = userProfile,
            topTracks = topTracks,
            topArtists = topArtists.toUI(),
            topGenres = topGenres.toImmutableList(),
            recentlyPlayed = recentlyPlayed.toImmutableList()
        )
    }
        .flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeScreenState()
        )

    private fun launchSyncWorker() {
        viewModelScope.launch {
            _event.send(Event.LaunchSyncWorker)
        }
    }

    fun forceSync() {
        viewModelScope.launch {
            _event.send(Event.LaunchSyncWorker)
        }
    }

    sealed interface Event {
        object LaunchSyncWorker : Event
    }

}