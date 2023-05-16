package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.collectEvents
import com.wachon.spotiwrap.data.worker.Sync
import com.wachon.spotiwrap.features.artists.presentation.homeartists.HomeTopArtists
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    listState: LazyListState
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    collectEvents {
        viewModel.event.collectLatest {
            when (it) {
                HomeViewModel.Event.LaunchSyncWorker -> Sync.initialize(context)
            }
        }
    }

    HomeContent(
        state = state,
        listState = listState
    )
}

@Composable
fun HomeContent(
    state: MenuScreenState,
    listState: LazyListState
) {

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Top
    ) {
        item { ProfileTopBar(user = state.userProfile) }
        item { HomeTopTracks(tracks = state.topTracks) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { HomeTopArtists(artists = state.topArtists) }
        item { Spacer(modifier = Modifier.height(500.dp)) }
    }
}
