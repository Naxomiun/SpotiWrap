package com.wachon.spotiwrap.features.presentation.album

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.AlbumItem
import com.wachon.spotiwrap.core.design.components.FitInfoBoxGraph
import com.wachon.spotiwrap.core.design.components.FitInfoBoxText
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.SpotifyOpenButton
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.LargeTitle
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.core.design.ui.toItemUI
import com.wachon.spotiwrap.features.artists.presentation.common.ArtistItem
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel = koinViewModel(),
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedAlbumContent(
        state = state,
        listState = listState,
        onBackPressed = onBackPressed,
        onTrackSelected = onTrackSelected,
        onArtistSelected = onArtistSelected,
    )
}

@Composable
fun AnimatedAlbumContent(
    state: AlbumScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> AlbumContent(
                state = state,
                listState = listState,
                onBackPressed = onBackPressed,
                onTrackSelected = onTrackSelected,
                onArtistSelected = onArtistSelected,
            )
        }
    }
}

@Composable
fun AlbumContent(
    state: AlbumScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.background(Color.Black),
        state = listState,
        verticalArrangement = Arrangement.Top,
    ) {
        item { AlbumPhoto(image = state.album?.albumImageUrl ?: "", onBackPressed = onBackPressed) }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { AlbumTitle(name = state.album?.albumName ?: "") }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            AlbumStats(
                tracks = state.album?.albumTotalTracks ?: 0,
                duration = state.album?.albumDuration ?: "",
                popularity = state.album?.albumPopularity ?: 0,
                date = state.album?.albumReleaseDate ?: "",
            )
        }
        item {
            if (state.album?.albumGenres?.isNotEmpty() == true) {
                Spacer(modifier = Modifier.height(24.dp))
                AlbumGenres(genres = state.album.albumGenres)
            }
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        AlbumTracks(
            topTracks = state.album?.albumTracks ?: emptyList(),
            onTrackSelected = onTrackSelected
        )
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { AlbumLabel(label = state.album?.albumLabel ?: "") }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item {
            AlbumArtists(
                related = state.album?.albumArtists ?: emptyList(),
                onArtistSelected = onArtistSelected
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { SpotifyOpenButton(url = state.album?.albumExternalUrl ?: "") }
        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}

@Composable
fun AlbumPhoto(
    image: String, onBackPressed: () -> Unit
) {
    Box(contentAlignment = Alignment.TopStart) {
        AsyncImage(
            model = image,
            contentDescription = image,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
        )
        Icon(
            modifier = Modifier
                .width(50.dp)
                .size(50.dp)
                .padding(top = 24.dp)
                .clickable { onBackPressed.invoke() },
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
        )
    }
}

@Composable
fun AlbumTitle(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        TextNoPadding(
            text = name,
            style = LargeTitle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun AlbumStats(popularity: Int, tracks: Int, duration: String, date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        FitInfoBoxText(
            data = tracks.toString(),
            dataName = "tracks",
            modifier = Modifier
                .weight(1f)
        )

        FitInfoBoxGraph(
            data = popularity,
            dataName = "popularity",
            modifier = Modifier
                .weight(1f)
        )
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        FitInfoBoxText(
            data = duration,
            dataName = "duration",
            modifier = Modifier
                .weight(1f)
        )

        FitInfoBoxText(
            data = date,
            dataName = "date",
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun AlbumGenres(genres: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        TextNoPadding(
            text = "Genres",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )

        LazyRow(
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(genres) {
                SuggestionChip(
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = SpotifyBlack
                    ),
                    onClick = {},
                    label = {
                        TextNoPadding(
                            text = it,
                            style = SmallTitle.copy(fontSize = 14.sp),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                )
            }
        }
    }
}

context(LazyListScope)
fun AlbumTracks(topTracks: List<TrackModel>, onTrackSelected: (String) -> Unit) {
    item {
        TextNoPadding(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Albums' Tracks",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    item { Spacer(modifier = Modifier.height(4.dp)) }
    items(topTracks) { item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onTrackSelected.invoke(item.id) }
        ) {
            AlbumItem(item = item.toItemUI())
        }
    }
}

@Composable
fun AlbumLabel(label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        TextNoPadding(
            text = "© $label",
            style = Body,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun AlbumArtists(related: List<ArtistModel>, onArtistSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        TextNoPadding(
            text = "Artists",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            state = rememberLazyListState(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = related,
                key = { it.id }
            ) { artist ->
                ArtistItem(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null
                        ) {
                            onArtistSelected.invoke(artist.id)
                        },
                    artist = artist.toUI()
                )
            }
        }
    }
}