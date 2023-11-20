package com.wachon.spotiwrap.features.collage.presentation.components

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.LargeTitle
import com.wachon.spotiwrap.core.design.theme.SubBody

@Composable
fun TopScreenContent(
    time: String,
    artists: List<ArtistModel>,
    albums: List<TrackModel>,

    ) {
    Column(
        modifier = Modifier
            .height(400.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TimeTitle(time = time)

        Spacer(modifier = Modifier.height(16.dp))
        ArtistTitle()

        Spacer(modifier = Modifier.height(8.dp))
        ArtistContent(artists = artists)

        Spacer(modifier = Modifier.height(32.dp))
        TrackTitle()

        Spacer(modifier = Modifier.height(8.dp))
        TrackContent(albums = albums)
    }
}

@Composable
private fun TimeTitle(time: String) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextWithLine(
            text = getTimeStringForTitle(time),
            textStyle = LargeTitle
        )
    }
}

private fun getTimeStringForTitle(time: String) = when (time) {
    TopItemTimeRange.SHORT_TERM.name -> "Last Month"
    TopItemTimeRange.MEDIUM_TERM.name -> "Last 6 Months"
    TopItemTimeRange.LONG_TERM.name -> "Lifetime"
    else -> ""
}

@Composable
private fun ArtistTitle() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        TextWithLine(
            text = "Top Artists"
        )
    }
}

@Composable
private fun ArtistContent(artists: List<ArtistModel>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = artists.take(3),
        ) {
            Column(
                modifier = Modifier.width(128.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = it.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = it.name,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "#${artists.indexOf(it) + 1} ${it.name}",
                    style = SubBody.copy(fontSize = 12.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun TrackTitle() {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
    ) {
        TextWithLine(
            text = "Top Tracks"
        )
    }
}

@Composable
private fun TrackContent(albums: List<TrackModel>) {
    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = albums.take(3)
        ) {
            Column(
                modifier = Modifier.width(128.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = it.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = it.title,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "#${albums.indexOf(it) + 1} ${it.title}",
                    style = SubBody.copy(fontSize = 12.sp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
