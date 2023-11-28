package com.wachon.spotiwrap.features.presentation.artist

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
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.wachon.spotiwrap.core.common.model.AlbumModel
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.FitInfoBoxGraph
import com.wachon.spotiwrap.core.design.components.FitInfoBoxText
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.SpotifyOpenButton
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.components.TopItem
import com.wachon.spotiwrap.core.design.theme.LargeTitle
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.core.design.ui.toItemUI
import com.wachon.spotiwrap.features.artists.presentation.common.ArtistItem
import com.wachon.spotiwrap.features.artists.presentation.model.toUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArtistScreen(
    viewModel: ArtistViewModel = koinViewModel(),
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedArtistContent(
        state = state,
        listState = listState,
        onBackPressed = onBackPressed,
        onTrackSelected = onTrackSelected,
        onAlbumSelected = onAlbumSelected,
        onArtistSelected = onArtistSelected,
    )
}

@Composable
fun AnimatedArtistContent(
    state: ArtistScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> ArtistContent(
                state = state,
                listState = listState,
                onBackPressed = onBackPressed,
                onTrackSelected = onTrackSelected,
                onAlbumSelected = onAlbumSelected,
                onArtistSelected = onArtistSelected,
            )
        }
    }
}

@Composable
fun ArtistContent(
    state: ArtistScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onTrackSelected: (String) -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.background(Color.Black),
        state = listState,
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            ArtistPhoto(
                image = state.artist?.artistImage ?: "", onBackPressed = onBackPressed
            )
        }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { ArtistTitle(name = state.artist?.artistName ?: "") }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            ArtistStats(
                followers = state.artist?.artistFollowers ?: 0,
                popularity = state.artist?.artistPopularity ?: 0
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { ArtistGenres(genres = state.artist?.artistGenres ?: emptyList()) }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        ArtistTopTracks(topTracks = state.topTracks.take(3), onTrackSelected = onTrackSelected)
        item { Spacer(modifier = Modifier.height(24.dp)) }
        ArtistTopAlbums(topAlbums = state.albums.take(3), onAlbumSelected = onAlbumSelected)
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { RelatedArtists(related = state.related, onArtistSelected = onArtistSelected) }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { SpotifyOpenButton(url = state.artist?.artistExternalUrl ?: "") }
        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}

@Composable
fun ArtistPhoto(
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
fun ArtistTitle(name: String) {
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
fun ArtistStats(followers: Int, popularity: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        FitInfoBoxText(
            data = followers.toString(),
            dataName = "followers",
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
}

@Composable
fun ArtistGenres(genres: List<String>) {
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
fun ArtistTopTracks(topTracks: List<TrackModel>, onTrackSelected: (String) -> Unit) {
    item {
        TextNoPadding(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Top Tracks",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    item { Spacer(modifier = Modifier.height(4.dp)) }
    itemsIndexed(topTracks) { index, item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onTrackSelected.invoke(item.id) }
        ) {
            TopItem(index = index + 1, item = item.toItemUI())
        }
    }
}

context(LazyListScope)
fun ArtistTopAlbums(topAlbums: List<AlbumModel>, onAlbumSelected: (String) -> Unit) {
    item {
        TextNoPadding(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = "Top Albums",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
    item { Spacer(modifier = Modifier.height(4.dp)) }
    itemsIndexed(topAlbums) { index, item ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onAlbumSelected.invoke(item.id) }
        ) {
            TopItem(index = index + 1, item = item.toItemUI())
        }
    }
}

@Composable
fun RelatedArtists(related: List<ArtistModel>, onArtistSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {

        TextNoPadding(
            text = "Related Artists",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

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