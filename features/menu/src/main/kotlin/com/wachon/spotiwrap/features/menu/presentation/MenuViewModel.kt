package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.data.User
import com.wachon.spotiwrap.features.menu.domain.GetTokenUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserProfile: GetUserProfileUseCase,
    private val getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        viewModelScope.launch {
            getTokenUseCase()?.let {
                getCurrentProfile(it)
                getTop(it, MenuCategory.TRACKS)
                getTop(it, MenuCategory.ARTISTS)
            }
        }
    }

    private suspend fun getCurrentProfile(token: String) {
        val user = scope.async {
            getUserProfile(token)
        }

        _state.update {
            it.copy(
                profile = user.await()
            )
        }
    }

    private suspend fun getTop(token: String, category: MenuCategory) {
        val top = scope.async {
            getUserTopItemsUseCase(token, category, 5, 0, "medium_term")
        }

        _state.update {
            when (category) {
                MenuCategory.TRACKS -> it.copy(topTracks = top.await())
                MenuCategory.ARTISTS -> it.copy(topArtists = top.await())
            }
        }
    }

    fun onCategorySelected(category: MenuCategory) {
        _state.update {
            it.copy(
                categorySelected = category
            )
        }
    }

    data class State(
        val loading: Boolean = false,
        val profile: User? = null,
        val topTracks: Top? = null,
        val topArtists: Top? = null,
        val categories: List<MenuCategory> = listOf(MenuCategory.TRACKS, MenuCategory.ARTISTS),
        val categorySelected: MenuCategory = MenuCategory.TRACKS
    )
}