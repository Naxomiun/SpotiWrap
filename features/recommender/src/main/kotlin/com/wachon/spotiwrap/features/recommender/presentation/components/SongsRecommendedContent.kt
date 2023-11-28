package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SubBody

context(LazyListScope)
fun SongsRecommendedContent(
    isLoadingRecommendations: Boolean,
    title: String,
    tracks: List<TrackModel>,
    onTrackClicked: (TrackModel) -> Unit,
    onRefreshClicked: () -> Unit,
) {
    item { Spacer(modifier = Modifier.height(16.dp)) }
    item {
        SongsRecommendedTitle(
            shouldShowIcon = tracks.isNotEmpty(),
            title = title,
            onRefreshClicked = onRefreshClicked
        )
    }
    item { Spacer(modifier = Modifier.height(16.dp)) }
    item {
        if (isLoadingRecommendations) {
            LoadingView()
        } else if (tracks.isEmpty()) {
            SongRecommendedEmpty()
        } else {
            SongRecommendedList(
                tracks = tracks,
                onTrackClicked = onTrackClicked
            )
        }
    }
}

@Composable
fun SongsRecommendedTitle(
    modifier: Modifier = Modifier,
    shouldShowIcon: Boolean,
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
                .alpha(if (shouldShowIcon) 1f else 0f),
        )
    }
}

context(LazyListScope)
fun SongRecommendedList(
    tracks: List<TrackModel>,
    onTrackClicked: (TrackModel) -> Unit
) {
    items(tracks) { track ->
        SongRecommendedItem(
            item = track,
            onTrackClicked = onTrackClicked,
        )
    }
}

@Composable
fun SongRecommendedItem(
    item: TrackModel,
    onTrackClicked: (TrackModel) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(50.dp)
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.weight(0.1F))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = item.title,
                style = Body.copy(fontWeight = FontWeight.W600, fontSize = 16.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = item.artists,
                style = SubBody.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.weight(0.1F))

        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(32.dp)
                .clickable { onTrackClicked.invoke(item) }
        )
    }
}

@Composable
fun SongRecommendedEmpty() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select a Playlist :)",
            color = MaterialTheme.colorScheme.onBackground,
            style = Body
        )
    }
}

@Preview
@Composable
fun SongRecommendedItemPreview() {
    SongRecommendedItem(
        item = TrackModel(
            id = "asdasd",
            fame = ItemFame.NONE,
            imageUrl = "",
            title = "Kemba Walker",
            artists = "Eladio Carrion, Bad Bunny",
            artistsIds = listOf(),
            uri = "asdasda",
            album = "",
            albumId = "",
            duration = "",
            popularity = 0,
            externalUrl = ""
        ),
        onTrackClicked = { trackModel -> }
    )
}