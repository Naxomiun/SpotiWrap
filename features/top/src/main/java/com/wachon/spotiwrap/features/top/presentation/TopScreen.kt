package com.wachon.spotiwrap.features.top.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.ScreenTitle
import com.wachon.spotiwrap.core.design.components.SingleChoiceTimeButton
import com.wachon.spotiwrap.core.design.components.SingleChoiceTypeButton
import com.wachon.spotiwrap.core.design.components.TopList
import com.wachon.spotiwrap.core.design.extensions.customHaze
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopScreen(
    viewModel: TopViewModel = koinViewModel(),
    hazeStateProvider: () -> HazeState,
    listState: LazyListState,
    onTrackSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingView()
    } else {
        TopContent(
            listState = listState,
            state = state,
            viewModel = viewModel,
            hazeStateProvider = hazeStateProvider,
            onTrackSelected = onTrackSelected,
            onArtistSelected = onArtistSelected,
        )
    }
}

@Composable
fun TopContent(
    listState: LazyListState,
    state: TopScreenState,
    viewModel: TopViewModel,
    hazeStateProvider: () -> HazeState,
    onTrackSelected: (String) -> Unit,
    onArtistSelected: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .systemBarsPadding()
            .customHaze(
                hazeStateProvider = hazeStateProvider,
                backgroundColor = MaterialTheme.colorScheme.surface
            ),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        ScreenTitle(text = LocalContext.current.getString(R.string.top_title))
        Spacer(modifier = Modifier.height(8.dp))
        SingleChoiceTypeButton(options = state.types) { viewModel.selectType(it) }
        SingleChoiceTimeButton(options = state.times) { viewModel.selectTime(it) }
        TopList(listState = listState, content = state.content) {
            if (state.typeSelected == TopItemType.TRACKS) {
                onTrackSelected.invoke(it)
            } else {
                onArtistSelected.invoke(it)
            }
        }
    }
}