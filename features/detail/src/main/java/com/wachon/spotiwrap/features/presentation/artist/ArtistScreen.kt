package com.wachon.spotiwrap.features.presentation.artist

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun ArtistScreen(
    viewModel: ArtistViewModel = koinViewModel(),
    listState: LazyListState,
    id: Int
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

}