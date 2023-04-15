package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.data.User
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

//TODO: Dont use raw dispatchers
class MenuViewModel(
    private val getUserProfile: GetUserProfileUseCase,
    private val getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    init {
        viewModelScope.launch {
            getCurrentProfile()
            getTop(MenuCategory.TRACKS)
            getTop(MenuCategory.ARTISTS)
        }
    }

    private fun getCurrentProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserProfile()
            _state.update {
                it.copy(
                    profile = user
                )
            }
        }
    }

    fun getTop(category: MenuCategory) {
        viewModelScope.launch(Dispatchers.IO) {
            val top = getUserTopItemsUseCase(
                type = category,
                limit = 10,
                offset = state.value.currentOffset,
                timeRange = "medium_term"
            )
            _state.update {
                when (category) {
                    MenuCategory.TRACKS -> it.copy(topTracks = top)
                    MenuCategory.ARTISTS -> it.copy(topArtists = top)
                }
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

    fun getCategorySelected(): MenuCategory {
        return state.value.categorySelected
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