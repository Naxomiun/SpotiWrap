package com.wachon.spotiwrap.features.recently.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SubBody
import kotlinx.collections.immutable.ImmutableList

context(LazyListScope)
fun HomeRecentlyPlayed(
    tracks: ImmutableList<TrackModel>,
    onTrackSelected: (String) -> Unit,
) {
    item { Spacer(modifier = Modifier.height(16.dp)) }
    item { HeaderRecentlyPlayed() }
    item { Spacer(modifier = Modifier.height(8.dp)) }
    items(tracks) { track ->
        RecentlyPlayedItem(
            track = track,
            onTrackSelected = onTrackSelected
        )
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
            text = LocalContext.current.getString(R.string.home_top_recently_played_title)
        )
    }
}

@Composable
fun RecentlyPlayedItem(
    track: TrackModel,
    onTrackSelected: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onTrackSelected.invoke(track.id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = track.imageUrl,
            contentDescription = track.title,
            modifier = Modifier
                .size(50.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            Text(
                text = track.title,
                style = Body.copy(fontWeight = FontWeight.W600),
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
                .weight(0.15f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = track.playedAt,
                style = SubBody.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
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
            artistsIds = listOf(),
            album = "",
            albumId = "",
            uri = "asdasda",
            duration = "",
            popularity = 0,
            externalUrl = ""
        )
    ) {}
}