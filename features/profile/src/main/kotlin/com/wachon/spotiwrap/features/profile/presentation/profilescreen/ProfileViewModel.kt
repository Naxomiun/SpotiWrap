package com.wachon.spotiwrap.features.profile.presentation.profilescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.DispatcherProvider
import com.wachon.spotiwrap.features.profile.domain.GetUserPlaylistsUseCase
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.profile.presentation.model.toUI
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(
    dispatcherProvider: DispatcherProvider,
    getUserProfile: GetUserProfileUseCase,
    getUserPlaylists: GetUserPlaylistsUseCase,
) : ViewModel() {

    private val _event = Channel<Event>()
    val event = _event.receiveAsFlow()

    private val userProfile = getUserProfile()
        .map { it?.toUI() }

    private val userPlaylists = getUserPlaylists()

    val uiState = combine(
        userProfile,
        userPlaylists
    ) { profile, playlists ->
        ProfileScreenState(
            userProfile = profile,
            userPlaylists = playlists.toImmutableList()
        )

    }.flowOn(dispatcherProvider.background)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ProfileScreenState()
        )

    sealed interface Event

}