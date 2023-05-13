package com.wachon.spotiwrap.features.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.features.artists.presentation.homeartists.HomeTopArtists
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import kotlinx.collections.immutable.ImmutableList
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    listState: LazyListState
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    HomeContent(
        state = state,
        listState = listState
    )
}

@Composable
fun HomeContent(
    state: HomeScreenState,
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
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { HomeTopGenres(genres = state.topGenres) }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeTopGenres(
    genres: ImmutableList<String>
) {
    LazyHorizontalStaggeredGrid(rows = StaggeredGridCells.Fixed(3)) {
        items(genres) { genre ->
            AssistChip(
                onClick = { },
                label = {
                    Text(
                        genre,
                        style = SubBody
                    )
                },
                leadingIcon = {
                    Image(
                        rememberVectorPainter(image = ),
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }
    }
}
