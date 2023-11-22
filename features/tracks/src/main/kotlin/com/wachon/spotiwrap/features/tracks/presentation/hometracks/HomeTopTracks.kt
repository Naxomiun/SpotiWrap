package com.wachon.spotiwrap.features.tracks.presentation.hometracks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.tracks.presentation.common.TrackItem
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeTopTracks(
    modifier: Modifier = Modifier,
    tracks: ImmutableList<TrackUI>
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTopTrackList()
        Spacer(modifier = Modifier.height(8.dp))
        TopTrackList(tracks = tracks)
    }

}

@Composable
fun HeaderTopTrackList(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        TextWithLine(
            text = "Top Tracks"
        )
    }

}

@Composable
fun TopTrackList(
    modifier: Modifier = Modifier,
    tracks: ImmutableList<TrackUI>
) {
    LazyRow(
        state = rememberLazyListState(),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = tracks,
            key = { it.trackId }
        ) { track ->
            TrackItem(
                track = track
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTopTracksPreview() {
    SpotiWrapTheme {
        HomeTopTracks(
            tracks = persistentListOf()
        )
    }
}