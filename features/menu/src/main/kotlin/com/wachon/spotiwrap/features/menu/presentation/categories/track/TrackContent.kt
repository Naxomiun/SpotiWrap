package com.wachon.spotiwrap.features.menu.presentation.categories.track

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun TracksContent(
    modifier: Modifier = Modifier, viewModel: TrackViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LazyColumn {
        items(count = state.top?.items?.size ?: 0) { index ->
            val item = state.top?.items?.get(index % state.top?.items!!.size)
            Text(text = item?.name.toString())
        }
    }
}