package com.wachon.spotiwrap.features.top.presentation

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.ScreenTitle
import com.wachon.spotiwrap.core.design.components.TopList
import com.wachon.spotiwrap.features.top.presentation.components.TimesTabs
import com.wachon.spotiwrap.features.top.presentation.components.TypesTabs
import org.koin.androidx.compose.koinViewModel

@Composable
fun TopScreen(
    listState: LazyListState,
    viewModel: TopViewModel = koinViewModel(),
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.isLoading) {
        LoadingView()
    } else {
        TopContent(
            listState = listState,
            context = context,
            state = state,
            viewModel = viewModel,
        )
    }
}

@Composable
fun TopContent(
    listState: LazyListState,
    context: Context,
    state: TopScreenState,
    viewModel: TopViewModel,
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        ScreenTitle(text = "Top List")
        Spacer(modifier = Modifier.height(16.dp))
        TypesTabs { viewModel.selectType(it) }
        TimesTabs { viewModel.selectTime(it) }
        TopList(listState = listState, content = state.content)
    }
}