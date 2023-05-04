package com.wachon.spotiwrap.features.menu.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wachon.spotiwrap.core.common.flow.WhileSubscribedOrRetained
import com.wachon.spotiwrap.features.profile.domain.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MenuViewModel(
    getUserProfile: GetUserProfileUseCase,
) : ViewModel() {

    private val selectedCategory = MutableStateFlow(MenuCategory.TRACKS)
    private val userProfile = getUserProfile()

    val uiState: StateFlow<MenuScreenState> = combine(
        selectedCategory,
        userProfile
    ) { selectedCategory, profile ->
        MenuScreenState(
            loading = false,
            selectedCategory = selectedCategory,
            userProfile = profile
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MenuScreenState()
    )

    fun onCategorySelected(category: MenuCategory) {
        selectedCategory.update { category }
    }

}