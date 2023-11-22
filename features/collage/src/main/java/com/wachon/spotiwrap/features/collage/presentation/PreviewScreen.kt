package com.wachon.spotiwrap.features.collage.presentation

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.collage.presentation.components.CollageHeader
import com.wachon.spotiwrap.features.collage.presentation.components.TopPreview
import com.wachon.spotiwrap.features.collage.presentation.utils.BitmapUtil.shareImage
import org.koin.androidx.compose.koinViewModel


@Composable
fun PreviewScreen(
    viewModel: PreviewViewModel = koinViewModel(), listState: LazyListState
) {
    val context = LocalContext.current
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    if (state.isLoading) {
        LoadingView()
    } else {
        PreviewContent(context = context, state = state, viewModel = viewModel)
    }
}

@Composable
fun PreviewContent(
    context: Context,
    state: PreviewScreenState,
    viewModel: PreviewViewModel
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        PreviewTitle {
            shareImage(context = context, bitmap = viewModel.getPreviewBitmap())
        }
        Spacer(modifier = Modifier.height(32.dp))
        TopPreview(
            timeIndex = state.timeIndex,
            time = state.time,
            artists = state.artists,
            artistsCovers = state.artistsCovers,
            albums = state.albums,
            albumsCovers = state.albumsCovers,
            optionIndex = state.optionIndex,
            typeIndex = state.typeIndex,
            sizeIndex = state.sizeIndex
        ) {
            viewModel.storePreviewBitmap(it)
        }
        Spacer(modifier = Modifier.height(64.dp))
        CollageOptions(
            timeIndex = state.timeIndex,
            time = state.time,
            optionIndex = state.optionIndex,
            options = state.options,
            typeIndex = state.typeIndex,
            types = state.types,
            sizeIndex = state.sizeIndex,
            sizes = state.sizes,
            onTimeSelect = { viewModel.changeTimeIndex(it) },
            onOptionSelect = { viewModel.changeOptionIndex(it) },
            onTypeSelect = { viewModel.changeTypeIndex(it) },
            onSizeSelect = { viewModel.changeSizeIndex(it) },
        )

    }
}

@Composable
fun CollageOptions(
    timeIndex: Int,
    time: List<TopItemTimeRange>,
    optionIndex: Int,
    options: List<CollageOptionsEnum>,
    typeIndex: Int,
    types: List<CollageTypesEnum>,
    sizeIndex: Int,
    sizes: List<CollageSizesEnum>,
    onTimeSelect: (Int) -> Unit,
    onOptionSelect: (Int) -> Unit,
    onTypeSelect: (Int) -> Unit,
    onSizeSelect: (Int) -> Unit,
) {
    CollageHeader(
        timeIndex = timeIndex,
        time = time,
        optionIndex = optionIndex,
        options = options,
        typeIndex = typeIndex,
        types = types,
        sizeIndex = sizeIndex,
        sizes = sizes,
        onTimeSelect = onTimeSelect,
        onOptionSelect = onOptionSelect,
        onTypeSelect = onTypeSelect,
        onSizeSelect = onSizeSelect,
    )
}

@Composable
fun PreviewTitle(
    onShareClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Top Preview",
            style = Title,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Icon(
            imageVector = Icons.Filled.Share,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    onShareClicked.invoke()
                }
        )
    }
}
