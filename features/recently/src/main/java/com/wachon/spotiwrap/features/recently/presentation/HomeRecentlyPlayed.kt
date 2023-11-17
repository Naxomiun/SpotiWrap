package com.wachon.spotiwrap.features.recently.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SubBody
import kotlinx.collections.immutable.ImmutableList
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun HomeRecentlyPlayed(
    modifier: Modifier = Modifier,
    tracks: ImmutableList<TrackModel>,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderRecentlyPlayed()
        Spacer(modifier = Modifier.height(8.dp))
        RecentlyPlayedList(tracks = tracks)
    }
}

@Composable
fun HeaderRecentlyPlayed(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        TextWithLine(
            text = "Recently Played"
        )
    }
}

@Composable
fun RecentlyPlayedList(
    modifier: Modifier = Modifier,
    tracks: List<TrackModel>
) {
    Column(
        modifier = modifier,
    ) {
        tracks.forEach { track ->
            RecentlyPlayedItem(
                track = track
            )
        }
    }
}

@Composable
fun RecentlyPlayedItem(
    track: TrackModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = track.imageUrl,
            contentDescription = track.title,
            modifier = Modifier
                .size(50.dp)
                .clip(MaterialTheme.shapes.small)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = track.title,
                style = Body.copy(fontSize = 16.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = track.artists,
                style = SubBody.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(0.1f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = calculateTime(track.playedAt),
                style = SubBody.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

private fun calculateTime(timestamp: String): String {
    val formato = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    formato.timeZone = TimeZone.getTimeZone("UTC")

    try {
        val date = formato.parse(timestamp)
        val now = Date()

        val millisDiff = now.time - date.time
        val minutes = (millisDiff / (1000 * 60)).toInt()
        val hours = minutes / 60

        return if (minutes < 60) {
            "$minutes m"
        } else {
            "$hours h"
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return "Error calculating time"
    }
}

@Preview
@Composable
fun RecentlyPlayedItemPreview() {
    RecentlyPlayedItem(
        track = TrackModel(
            id = "asdasdasdasdasd",
            fame = ItemFame.NONE,
            imageUrl = "",
            title = "Kemba Walker",
            artists = "Eladio Carrion, Bad Bunny",
            album = "",
            uri = "asdasda"
        ),
    )
}