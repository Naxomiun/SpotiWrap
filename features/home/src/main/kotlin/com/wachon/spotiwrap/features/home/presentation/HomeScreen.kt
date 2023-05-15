package com.wachon.spotiwrap.features.home.presentation

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.features.artists.presentation.homeartists.HomeTopArtists
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import kotlinx.collections.immutable.ImmutableList
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
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
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(bottom = 120.dp),
    ) {
        item { ProfileTopBar(user = state.userProfile) }
        item { HomeTopTracks(tracks = state.topTracks) }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item { HomeTopArtists(artists = state.topArtists) }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        HomeTopGenres(genres = state.topGenres)
    }
}

context(LazyListScope)
fun HomeTopGenres(
    genres: ImmutableList<String>
) {
    item { Spacer(modifier = Modifier.height(16.dp)) }
    item {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier
                    .drawBehind {
                        drawLine(
                            color = BubblegumPink,
                            start = Offset(-20f, size.height / 1.6f),
                            end = Offset(size.width / 1.1F, size.height / 1.6f),
                            strokeWidth = size.height / 4
                        )
                    },
                text = "Top genres",
                style = Body.copy(fontWeight = FontWeight.W700)
            )
        }
    }
    itemsIndexed(
        items = genres,
        key = { _, genre -> genre }
    ) { index, genre ->

        val fraction = (1 + index).toFloat() / genres.size

        Box(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth(fraction = fraction)
                .background(color = BubblegumPink)
        ) {
            Text(text = genre, style = SubBody, modifier = Modifier.padding(8.dp))
        }
    }
}