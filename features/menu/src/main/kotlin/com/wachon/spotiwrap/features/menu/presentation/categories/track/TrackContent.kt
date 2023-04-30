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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin
import com.wachon.spotiwrap.core.design.components.BrandContainer
import com.wachon.spotiwrap.core.design.extensions.getBackgroundColorFromPalette
import com.wachon.spotiwrap.features.menu.domain.model.TrackModel
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TracksContent(
    state: MenuViewModel.State.TracksState,
    onReachedEnd: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel()
) {
  
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 50.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(state.tracks) { TrackItem(track = it) }
    }

}

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    track: TrackModel
) {

    var backgroundColor by remember {
        mutableStateOf(Color.White)
    }

    BrandContainer(
        modifier = modifier
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
                imageModel = { track.imageUrl },
                imageOptions = ImageOptions(
                    contentDescription = track.title
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
                    text = track.title,
                )

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = track.getFormattedArtists()
                )
            }
        }
    }

}
