package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.features.menu.data.User
import com.wachon.spotiwrap.features.menu.domain.GetTokenUseCase
import com.wachon.spotiwrap.features.menu.domain.GetUserProfileUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MenuViewModel(
    private val getTokenUseCase: GetTokenUseCase,
    private val getUserProfile: GetUserProfileUseCase
) : ViewModel() {

    val state: StateFlow<State> get() = _state
    private val _state = MutableStateFlow(State())

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        viewModelScope.launch {
            getTokenUseCase()?.let { getCurrentProfile(it) }
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
        val categories: List<MenuCategory> = listOf(MenuCategory.Wrap, MenuCategory.Artists),
        val categorySelected: MenuCategory = MenuCategory.Wrap
    )
}