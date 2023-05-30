package com.wachon.spotiwrap.features.recommender.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.model.TopItemType.ARTISTS
import com.wachon.spotiwrap.core.common.model.TopItemType.TRACKS
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.recommender.presentation.components.GenresContent
import com.wachon.spotiwrap.features.recommender.presentation.components.LimitContent
import com.wachon.spotiwrap.features.recommender.presentation.components.SearchContent
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecommenderScreen(
    viewModel: RecommenderViewModel = koinViewModel(), listState: LazyListState,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    RecommenderContent(state = state, viewModel = viewModel, listState = listState)
}

@Composable
fun RecommenderContent(
    state: RecommenderScreenState, viewModel: RecommenderViewModel, listState: LazyListState,
) {
    val checkedList = remember { mutableStateListOf<String>() }

    val limit = remember { mutableStateOf(10f) }

    LazyColumn(
        state = listState, verticalArrangement = Arrangement.Top
    ) {
        item { RecommenderTitle() }
        //TODO Adds playlist name input
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            GenresContent(
                genres = state.genres,
                rowsPerColumn = 4,
                checkedList = checkedList,
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            SearchContent(
                type = ARTISTS,
                query = state.artistsQuery,
                suggestions = state.artistsSuggestions,
                seeds = state.artistsSeeds,
                onQueryChanged = { viewModel.updateArtistQuery(it) },
                onSeedAdded = { viewModel.addArtistSeed(it) },
                onSeedRemoved = { viewModel.removeArtistSeed(it) },
                onSuggestionClicked = { viewModel.clearArtistsSuggestions() }
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            SearchContent(
                type = TRACKS,
                query = state.tracksQuery,
                suggestions = state.tracksSuggestions,
                seeds = state.tracksSeeds,
                onQueryChanged = { viewModel.updateTrackQuery(it) },
                onSeedAdded = { viewModel.addTrackSeed(it) },
                onSeedRemoved = { viewModel.removeTrackSeed(it) },
                onSuggestionClicked = { viewModel.clearTracksSuggestions() }
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { LimitContent(limit = limit) }
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
fun SubmitButton(
    checkedList: MutableList<String>, onSubmit: (List<String>) -> Unit,
) {
    Button(
        onClick = { onSubmit(checkedList) }, modifier = Modifier.padding(16.dp)
    ) {
        Text("Submit")
    }
}
