package com.wachon.spotiwrap.features.menu.presentation.categories.track


import android.util.Log
import androidx.annotation.ColorInt
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.wachon.spotiwrap.core.design.components.BrandContainer
import com.wachon.spotiwrap.core.design.extensions.getBackgroundColorFromPalette
import com.wachon.spotiwrap.core.design.extensions.isDark
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
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        trackList.let {
            items(it) { item ->
                TrackItem(track = item)

                LaunchedEffect(lazyListState) {
                    val isScrolledToTheEnd =
                        lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == it.size - 1
                    if (isScrolledToTheEnd) {
                        onReachedEnd()
                    }
                }

            }
        }
    }
}

@Composable
fun TrackItem(
    track: Item,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel()
) {

    var backgroundColor by remember {
        mutableStateOf(Color.White)
    }

    val textColor by remember {
        derivedStateOf {
            if (backgroundColor.toArgb().isDark()) {
                Color.White
            } else {
                Color.Black
            }
        }
    }

    BrandContainer(
        modifier = Modifier
            .fillMaxSize(),
        backgroundColor = backgroundColor,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CoilImage(
                modifier = Modifier
                    .height(75.dp)
                    .width(75.dp),
                imageModel = { track.album.images.first().url },
                imageOptions = ImageOptions(
                    contentDescription = track.album.name
                ),
                component = rememberImageComponent {
                    +PalettePlugin {
                        backgroundColor = it.getBackgroundColorFromPalette()
                    }
                }
            )
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = track.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = textColor
                    )
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = track.getArtistToShow(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textColor
                    )
                )
            }
        }
    }

}
