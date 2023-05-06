package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.BottomNavBar
import com.wachon.spotiwrap.core.design.components.BottomNavBarItem
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        state = state
    )
}

@Composable
fun HomeContent(
    state: MenuScreenState
) {
    Column(verticalArrangement = Arrangement.Top) {
        ProfileTopBar(user = state.userProfile)
        HomeTopTracks(tracks = state.topTracks)
    }
}
