package com.wachon.spotiwrap.features.menu.presentation.categories.artist

import androidx.lifecycle.ViewModel
import com.wachon.spotiwrap.features.menu.data.Top
import com.wachon.spotiwrap.features.menu.domain.GetUserTopItemsUseCase

class ArtistViewModel(
    private val getUserTopItemsUseCase: GetUserTopItemsUseCase
) : ViewModel() {

    data class State(
        val loading: Boolean = false,
        val top: Top? = null
    )
}