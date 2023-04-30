package com.wachon.spotiwrap.features.menu.presentation

import com.wachon.spotiwrap.core.common.model.UserProfileModel

data class MenuScreenState(
    val loading: Boolean = true,
    val userProfile: UserProfileModel? = null,
    val selectedCategory: MenuCategory = MenuCategory.TRACKS
)