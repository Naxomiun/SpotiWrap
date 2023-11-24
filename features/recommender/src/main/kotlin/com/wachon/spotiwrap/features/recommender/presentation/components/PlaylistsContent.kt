package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.common.model.OwnerModel
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink

@Composable
fun PlaylistsContent(
    modifier: Modifier = Modifier,
    title: String,
    playlists: List<PlaylistModel>,
    playlistSelected: String,
    onPlaylistClicked: (Boolean, PlaylistModel) -> Unit,
    onRefreshClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PlaylistsTitle(
            title = title,
            onRefreshClicked = onRefreshClicked
        )
        Spacer(modifier = Modifier.height(16.dp))
        PlaylistsList(
            playlists = playlists,
            playlistSelected = playlistSelected,
            onPlaylistClicked = onPlaylistClicked,
        )
    }
}

@Composable
fun PlaylistsTitle(
    modifier: Modifier = Modifier,
    title: String,
    onRefreshClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
            text = title,
            style = Body.copy(fontWeight = FontWeight.W700)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = Icons.Filled.Refresh,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(25.dp)
                .clickable { onRefreshClicked.invoke() }
        )
    }
}

@Composable
fun PlaylistsList(
    modifier: Modifier = Modifier,
    playlists: List<PlaylistModel>,
    playlistSelected: String,
    onPlaylistClicked: (Boolean, PlaylistModel) -> Unit,
) {
    val rowState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    LazyRow(
        state = rowState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            playlists
        ) { playlist ->
            PlaylistItem(
                modifier = Modifier
                    .fillMaxWidth(),
                isChecked = playlistSelected == playlist.id,
                playlist = playlist,
                onPlaylistClicked = { isChecked ->
                    if (isChecked) {
                        onPlaylistClicked.invoke(true, playlist)
                    } else {
                        onPlaylistClicked.invoke(false, playlist)
                    }
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaylistContentPreview() {
    PlaylistsContent(
        title = "Eeeeeeeeeee",
        playlists = listOf(
            PlaylistModel(
                "asdasdasdasd",
                "asdasdasdasd",
                "La mejor playlist",
                "La mejor descripción",
                OwnerModel("asdasdasd", "asdasdad", "Balta"),
                false,
                false,
                listOf(),
                "asdasdas",
                "asdasdasd",
            )
        ),
        playlistSelected = "",
        onPlaylistClicked = { hasTo, name -> },
        onRefreshClicked = {}
    )
}