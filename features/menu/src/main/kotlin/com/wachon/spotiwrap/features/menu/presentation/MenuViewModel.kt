package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.flow.WhileSubscribedOrRetained
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopArtistsUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopTracksUseCase
import com.wachon.spotiwrap.features.menu.domain.model.ArtistModel
import com.wachon.spotiwrap.features.menu.domain.model.TrackModel
import com.wachon.spotiwrap.features.menu.domain.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MenuViewModel(
    getUserProfile: GetUserProfileUseCase,
    getUserTopTracks: GetUserTopTracksUseCase,
    getUserTopArtists: GetUserTopArtistsUseCase,
) : ViewModel() {

    private val selectedCategory = MutableStateFlow(MenuCategory.TRACKS)
    private val userProfile = getUserProfile()
    private val topTracks = getUserTopTracks()
    private val topArtists = getUserTopArtists()

    val state: StateFlow<State> = combine(
        selectedCategory,
        userProfile,
        topTracks,
        topArtists
    ) { selectedCategory, profile, topTracks, topArtists ->

        val userState = State.UserState(
            loading = profile == null,
            profile = profile
        )

        val tracksState = State.TracksState(
            loading = topTracks == null,
            tracks = topTracks
        )

        val artistsState = State.ArtistsState(
            loading = topArtists == null,
            artists = topArtists
        )

        State(
            categorySelected = selectedCategory,
            userState = userState,
            tracksState = tracksState,
            artistsState = artistsState
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileSubscribedOrRetained,
        initialValue = State()
    )

    fun onCategorySelected(category: MenuCategory) {
        selectedCategory.update { category }
    }

    data class State(
        val userState: UserState = UserState(),
        val tracksState: TracksState = TracksState(),
        val artistsState: ArtistsState = ArtistsState(),
        val categories: List<MenuCategory> = listOf(MenuCategory.TRACKS, MenuCategory.ARTISTS),
        val categorySelected: MenuCategory = MenuCategory.TRACKS
    ) {
        data class TracksState(
            val loading: Boolean = true,
            val tracks: List<TrackModel> = emptyList()
        )

        data class UserState(
            val loading: Boolean = true,
            val profile: UserModel? = null
        )

        data class ArtistsState(
            val loading: Boolean = true,
            val artists: List<ArtistModel> = emptyList()
        )
    }

}