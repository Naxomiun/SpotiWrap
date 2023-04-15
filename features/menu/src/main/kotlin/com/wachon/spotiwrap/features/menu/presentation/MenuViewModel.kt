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
            getTokenUseCase()?.let { token ->

                _state.update {
                    it.copy(
                        token = token
                    )
                }

                getCurrentProfile()
                getTop(MenuCategory.TRACKS)
                getTop(MenuCategory.ARTISTS)
            }
        }
    }

    private suspend fun getCurrentProfile() {
        viewModelScope.launch {
            val user = scope.async {
                getUserProfile(getToken())
            }

            _state.update {
                it.copy(
                    profile = user.await()
                )
            }
        }
    }

    fun getTop(category: MenuCategory) {
        viewModelScope.launch {
            val top = scope.async {
                getUserTopItemsUseCase(
                    token = getToken(),
                    type = category,
                    limit = 10,
                    offset = state.value.currentOffset,
                    timeRange = "medium_term"
                )
            }

            _state.update {
                when (category) {
                    MenuCategory.TRACKS -> it.copy(topTracks = top.await())
                    MenuCategory.ARTISTS -> it.copy(topArtists = top.await())
                }
            }
        }
    }

    private fun getToken(): String {
        return state.value.token
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
        val token: String = "",
        val topTracks: Top? = null,
        val topArtists: Top? = null,
        var currentOffset: Int = 0,
        val categories: List<MenuCategory> = listOf(MenuCategory.TRACKS, MenuCategory.ARTISTS),
        val categorySelected: MenuCategory = MenuCategory.TRACKS
    )
}