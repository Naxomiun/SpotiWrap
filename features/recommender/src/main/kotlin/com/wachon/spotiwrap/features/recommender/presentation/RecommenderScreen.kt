package com.wachon.spotiwrap.features.recommender.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.recommender.presentation.components.PlaylistsContent
import com.wachon.spotiwrap.features.recommender.presentation.components.SongsRecommendedContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecommenderScreen(
    viewModel: RecommenderViewModel = koinViewModel(), listState: LazyListState,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    if (state.isLoading) {
        LoadingView()
    } else if (state.playlists.isEmpty()) {
        RecommenderEmpty()
    } else {
        RecommenderContent(state = state, viewModel = viewModel, listState = listState)
    }
}

@Composable
fun RecommenderContent(
    state: RecommenderScreenState, viewModel: RecommenderViewModel, listState: LazyListState,
) {
    LazyColumn(
        state = listState, verticalArrangement = Arrangement.Top
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { RecommenderTitle() }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            PlaylistsContent(
                title = "Your Playlists",
                playlists = state.playlists,
                playlistSelected = state.playlistSelected?.id ?: "",
                onPlaylistClicked = { hasToSave, playlist ->
                    viewModel.updatePlaylistAndSongs(
                        hasToSave = hasToSave,
                        playlist = playlist
                    )
                }
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            SongsRecommendedContent(
                isLoadingRecommendations = state.isLoadingRecommendations,
                title = "Our recommendations",
                tracks = state.recommendations.take(10),
                onTrackClicked = { track ->
                    viewModel.addTrackToCurrentPlaylist(track)
                }
            ) {
                viewModel.refreshRecommendations()
            }
        }
    }
}

@Composable
fun RecommenderTitle() {
    Text(
        modifier = Modifier.padding(top = 24.dp, start = 24.dp),
        text = "Songs Recommender",
        style = Title,
        color = MaterialTheme.colorScheme.onBackground,
    )
}

@Composable
fun RecommenderEmpty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "You need to create a Playlist :)", color = BubblegumPink, style = Body)
    }
}
