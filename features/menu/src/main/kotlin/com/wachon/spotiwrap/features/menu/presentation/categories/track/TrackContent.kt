package com.wachon.spotiwrap.features.menu.presentation.categories.track

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.wachon.spotiwrap.features.menu.data.Item
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TracksContent(
    state: MenuViewModel.State,
    onReachedEnd: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel()
) {
    val trackList: MutableList<Item> = mutableListOf()
    state.topTracks?.items?.let { trackList.addAll(it) }
    val lazyListState = rememberLazyListState()


    LazyColumn(
        modifier = Modifier.fillMaxSize(), state = lazyListState, contentPadding = PaddingValues(16.dp)
    ) {
        trackList.let {
            items(it) { item ->
                TrackItem(track = item)

                LaunchedEffect(lazyListState) {
                    val isScrolledToTheEnd = lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == it.size - 1
                    if (isScrolledToTheEnd) {
                        onReachedEnd()
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrackItem(
    track: Item,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation()
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(), color = Color.White
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                GlideImage(
                    modifier = Modifier
                        .height(75.dp)
                        .width(75.dp)
                        .fillMaxWidth(),
                    model = track.album.images.first().url,
                    contentDescription = track.album.name,
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(), text = track.name, style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = track.getArtistToShow(),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}