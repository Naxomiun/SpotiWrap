package com.wachon.spotiwrap.features.recommender.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.ScreenTitle
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.features.recommender.presentation.components.PlaylistsContent
import com.wachon.spotiwrap.features.recommender.presentation.components.SongsRecommendedContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecommenderScreen(
    viewModel: RecommenderViewModel = koinViewModel(),
    listState: LazyListState,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedRecommenderContent(
        viewModel = viewModel,
        state = state,
        listState = listState
    )
}

@Composable
fun AnimatedRecommenderContent(
    viewModel: RecommenderViewModel,
    state: RecommenderScreenState,
    listState: LazyListState
) {
    AnimatedContent(
        targetState = state.isLoading,
        label = ""
    ) {
        when (it) {
            true -> LoadingView()
            false -> if (state.playlists.isEmpty()) {
                RecommenderEmpty()
            } else {
                RecommenderContent(state = state, viewModel = viewModel, listState = listState)
            }
        }
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
        item { ScreenTitle(text = "Songs Recommender") }
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
                },
                onRefreshClicked = { viewModel.refreshPlaylists() }
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        SongsRecommendedContent(
            isLoadingRecommendations = state.isLoadingRecommendations,
            title = "Our recommendations",
            tracks = state.recommendations,
            onTrackClicked = { track ->
                viewModel.addTrackToCurrentPlaylist(track)
            }
        ) {
            viewModel.refreshRecommendations()
        }
    }
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
