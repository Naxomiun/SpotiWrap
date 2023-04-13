package com.wachon.spotiwrap.features.menu.presentation.categories.track

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TracksContent(
    state: MenuViewModel.State,
    modifier: Modifier = Modifier,
    viewModel: TrackViewModel = koinViewModel()
) {
    val context = LocalContext.current

    LazyColumn {
        items(count = state.topTracks?.items?.size ?: 0) { index ->
            val item = state.topTracks?.items?.get(index % state.topTracks.items.size)
            Text(text = item?.name.toString())
        }
    }
}