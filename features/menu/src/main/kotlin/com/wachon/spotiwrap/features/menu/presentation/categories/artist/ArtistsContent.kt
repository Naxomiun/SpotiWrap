package com.wachon.spotiwrap.features.menu.presentation.categories.artist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.wachon.spotiwrap.features.menu.presentation.MenuViewModel

@Composable
fun ArtistsContent(
    state: MenuViewModel.State.ArtistsState,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    LazyColumn {
        items(state.artists) {
            Text(text = it.name)
        }
    }
}