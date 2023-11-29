package com.wachon.spotiwrap.features.presentation.track

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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.design.components.FitInfoBoxGraph
import com.wachon.spotiwrap.core.design.components.FitInfoBoxText
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.SpotifyOpenButton
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.components.TrackFeatures
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.LargeTitle
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.artists.presentation.common.ArtistItem
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import com.wachon.spotiwrap.features.tracks.presentation.model.AlbumUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun TrackScreen(
    viewModel: TrackViewModel = koinViewModel(),
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedTrackContent(
        state = state,
        listState = listState,
        onBackPressed = onBackPressed,
        onAlbumSelected = onAlbumSelected,
        onArtistSelected = onArtistSelected,
    )
}

@Composable
fun AnimatedTrackContent(
    state: TrackScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> TrackContent(
                state = state,
                listState = listState,
                onBackPressed = onBackPressed,
                onAlbumSelected = onAlbumSelected,
                onArtistSelected = onArtistSelected,
            )
        }
    }
}

@Composable
fun TrackContent(
    state: TrackScreenState,
    listState: LazyListState,
    onBackPressed: () -> Unit,
    onAlbumSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.background(Color.Black),
        state = listState,
        verticalArrangement = Arrangement.Top,
    ) {
        item {
            TrackPhoto(
                image = state.track?.trackImage ?: "", onBackPressed = onBackPressed
            )
        }
        item { Spacer(modifier = Modifier.height(32.dp)) }
        item { TrackTitle(name = state.track?.trackTitle ?: "") }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            TrackStats(
                time = state.track?.trackDuration ?: "",
                popularity = state.track?.trackPopularity ?: 0
            )
        }
        item {
            state.features?.let {
                Spacer(modifier = Modifier.height(24.dp))
                TrackFeatures(features = it)
            }
        }
        item {
            state.album?.let {
                Spacer(modifier = Modifier.height(16.dp))
                TrackAlbum(album = it, onAlbumSelected = onAlbumSelected)
            }
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            TrackArtist(
                artists = state.artists,
                onArtistSelected = onArtistSelected
            )
        }
        item { Spacer(modifier = Modifier.height(24.dp)) }
        item { SpotifyOpenButton(url = state.track?.trackExternalUrl ?: "") }
        item { Spacer(modifier = Modifier.height(64.dp)) }
    }
}

@Composable
fun TrackPhoto(
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
fun TrackTitle(name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        TextNoPadding(
            text = name,
            style = LargeTitle.copy(fontSize = 23.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun TrackStats(time: String, popularity: Int) {
    TextNoPadding(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        text = "Track Stats",
        style = Title.copy(fontSize = 20.sp),
        color = MaterialTheme.colorScheme.onSurface
    )

    Spacer(modifier = Modifier.height(4.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        FitInfoBoxText(
            data = time,
            dataName = "song duration",
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
fun TrackAlbum(album: AlbumUI, onAlbumSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onAlbumSelected.invoke(album.albumId) },
    ) {

        TextNoPadding(
            text = "Album",
            style = Title.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface
        )

        Spacer(modifier = Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp, end = 4.dp)
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(8)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                AsyncImage(
                    model = album.albumImageUrl,
                    contentDescription = album.albumName,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .size(100.dp)
                        .aspectRatio(1f)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        text = album.albumName,
                        style = Body.copy(fontWeight = FontWeight.W600),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    TextNoPadding(
                        modifier = Modifier.padding(top = 8.dp),
                        text = album.albumArtistsNames,
                        style = SubBody.copy(fontSize = 12.sp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

@Composable
fun TrackArtist(artists: List<ArtistUI>, onArtistSelected: (String) -> Unit) {
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
                items = artists,
                key = { it.artistId }
            ) { artist ->
                ArtistItem(
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onArtistSelected.invoke(artist.artistId)
                    },
                    artist = artist
                )
            }
        }
    }
}