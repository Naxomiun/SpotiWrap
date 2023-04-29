package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.dispatchers.BackgroundDispatcher.Background
import com.wachon.spotiwrap.core.common.flow.WhileSubscribedOrRetained
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.data.User
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//TODO: Refactor whole class
class MenuViewModel(
    getUserProfile: GetUserProfileUseCase,
    getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    private val selectedCategory = MutableStateFlow(MenuCategory.TRACKS)
    private val userProfile = getUserProfile()
    private val topTracks = getUserTopItemsUseCase(
        type = MenuCategory.TRACKS, limit = 10, offset = 0, timeRange = "medium_term"
    )
    private val topArtists = getUserTopItemsUseCase(
        type = MenuCategory.ARTISTS, limit = 10, offset = 0, timeRange = "medium_term"
    )

    val state: StateFlow<State> = combine(
        selectedCategory,
        userProfile,
        topTracks,
        topArtists
    ) { selectedCategory, profile, topTracks, topArtists ->
        State(
            categorySelected = selectedCategory,
            profile = profile,
            topTracks = topTracks,
            topArtists = topArtists
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
        val loading: Boolean = false,
        val profile: User? = null,
        val topTracks: Top? = null,
        val topArtists: Top? = null,
        var currentOffset: Int = 0,
        val categories: List<MenuCategory> = listOf(MenuCategory.TRACKS, MenuCategory.ARTISTS),
        val categorySelected: MenuCategory = MenuCategory.TRACKS
    )

}