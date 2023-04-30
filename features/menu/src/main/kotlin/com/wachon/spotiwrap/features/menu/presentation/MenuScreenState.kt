package com.wachon.spotiwrap.features.menu.presentation

import com.wachon.spotiwrap.core.common.model.UserModel

data class MenuScreenState(
    val loading: Boolean = true,
    val userProfile: UserModel? = null,
    val selectedCategory: MenuCategory = MenuCategory.TRACKS
)