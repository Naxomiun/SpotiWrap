package com.wachon.spotiwrap.features.artists.presentation.topartists

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.model.ArtistModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopArtistsScreen(
    viewModel: TopArtistsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    TopArtistsContent(state = state)
}

@Composable
fun TopArtistsContent(
    state: TopArtistsScreenState,
    modifier: Modifier = Modifier
) {
    when(state) {
        is TopArtistsScreenState.Loading -> Text(text = "Loading")
        is TopArtistsScreenState.Success -> TopArtistsList(state.artists)
    }
}

@Composable
fun TopArtistsList(
    artists: List<ArtistModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(artists) { artist ->
            Text(text = artist.name)
        }
    }
}