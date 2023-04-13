package com.wachon.spotiwrap.features.menu.presentation.categories.artist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel

@Composable
fun ArtistsContent(
    state: MenuViewModel.State,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn {
        items(count = state.topArtists?.items?.size ?: 0) { index ->
            val item = state.topArtists?.items?.get(index % state.topArtists.items.size)
            Text(text = item?.name.toString())
        }
    }
}