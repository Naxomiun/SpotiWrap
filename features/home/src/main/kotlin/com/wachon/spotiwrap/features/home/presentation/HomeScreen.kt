package com.wachon.spotiwrap.features.home.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.features.artists.presentation.homeartists.HomeTopArtists
import com.wachon.spotiwrap.features.home.domain.TopGenreUI
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import com.wachon.spotiwrap.features.tracks.presentation.hometracks.HomeTopTracks
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.delay
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
        item { Spacer(modifier = Modifier.height(16.dp)) }
        HomeTopGenres(genres = state.topGenres)
    }
}

context(LazyListScope)
fun HomeTopGenres(
    genres: ImmutableList<TopGenreUI>
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
        key = { _, genre -> genre.genreName }
    ) { index, genre ->

        var animateWidth by rememberSaveable {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = Unit) {
            if (animateWidth) return@LaunchedEffect
            animateWidth = true
        }

        val width by animateFloatAsState(
            (if (animateWidth) genre.genreChartValue else 0f), label = "",
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 100 * index,
                easing = LinearOutSlowInEasing
            )
        )

        BoxWithConstraints(
            Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .animateContentSize()
                    .clip(RoundedCornerShape(8.dp))
                    .height(24.dp)
                    .fillMaxWidth(fraction = width)
                    .background(color = BubblegumPink)
            )
            TextNoPadding(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterStart),
                text = genre.genreName,
                style = SubBody.copy(fontWeight = FontWeight.W700)
            )
        }
    }
}