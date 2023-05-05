package com.wachon.spotiwrap.features.menu.presentation


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    paddingValues: PaddingValues
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    MenuContent(
        state = state,
        paddingValues = paddingValues
    )
}

@Composable
fun MenuContent(
    state: MenuScreenState,
    paddingValues: PaddingValues,
) {
    Column(verticalArrangement = Arrangement.Top) {
        ProfileTopBar(
            user = state.userProfile,
            paddingValues = paddingValues,
        )
        HomeTopTracks(
            tracks = state.topTracks
        )
    }
}
