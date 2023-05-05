package com.wachon.spotiwrap.features.tracks.presentation.hometracks

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
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
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTopTrackList()
        Spacer(modifier = Modifier.height(8.dp))
        TopTrackList(
            tracks = tracks
        )
    }

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun HeaderTopTrackList(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
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
            text = "Top songs",
            style = Body.copy(fontWeight = FontWeight.W700)
        )
    }
}

@Composable
fun TopTrackList(
    modifier: Modifier = Modifier,
    tracks: ImmutableList<TrackUI>
) {

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(tracks) { track ->
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

fun DrawScope.drawWithLayer(block: DrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}