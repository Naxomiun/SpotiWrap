package com.wachon.spotiwrap.features.tracks.presentation.hometracks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theapache64.rebugger.Rebugger
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

    Rebugger(
        trackMap = mapOf(
            "tracks" to tracks
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Tus TOP canciones más escuchadas"
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tracks) { track ->
                TrackItem(
                    track = track
                )
            }
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