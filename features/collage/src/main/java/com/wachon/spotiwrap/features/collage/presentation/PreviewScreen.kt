package com.wachon.spotiwrap.features.collage.presentation

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import android.view.View
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
import androidx.core.view.drawToBitmap
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum.COLLAGE
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum.TOP
import com.wachon.spotiwrap.features.collage.presentation.components.CollageHeader
import com.wachon.spotiwrap.features.collage.presentation.components.FiveColumnCollage
import com.wachon.spotiwrap.features.collage.presentation.components.FourColumnCollage
import com.wachon.spotiwrap.features.collage.presentation.components.ThreeColumnCollage
import com.wachon.spotiwrap.features.collage.presentation.components.TopScreenContent
import org.koin.androidx.compose.koinViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

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
        PreviewTitle()
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
        )
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
fun TopPreview(
    timeIndex: Int,
    time: List<TopItemTimeRange>,
    artists: List<ArtistModel>,
    artistsCovers: List<String>,
    albums: List<TrackModel>,
    albumsCovers: List<String>,
    optionIndex: Int,
    typeIndex: Int,
    sizeIndex: Int,

    ) {
    when (optionIndex) {
        TOP.ordinal -> {
            TopScreen(
                time = time[timeIndex].name,
                artists = artists,
                albums = albums,
            )
        }

        COLLAGE.ordinal -> {
            CollageScreen(
                artistsCovers = artistsCovers,
                albumsCovers = albumsCovers,
                typeIndex = typeIndex,
                sizeIndex = sizeIndex
            )
        }
    }
}

@Composable
fun TopScreen(
    time: String,
    artists: List<ArtistModel>,
    albums: List<TrackModel>,
) {
    TopScreenContent(time = time, artists = artists, albums = albums)
}

@Composable
fun CollageScreen(
    artistsCovers: List<String>,
    albumsCovers: List<String>,
    typeIndex: Int,
    sizeIndex: Int,
) {
    val selectedCovers = if (typeIndex == CollageTypesEnum.ARTISTS.ordinal) {
        artistsCovers
    } else {
        albumsCovers
    }
    when (sizeIndex) {
        CollageSizesEnum.SMALL.ordinal -> {
            ThreeColumnCollage(
                covers = selectedCovers
            )
        }

        CollageSizesEnum.MEDIUM.ordinal -> {
            FourColumnCollage(
                covers = selectedCovers
            )
        }

        CollageSizesEnum.BIG.ordinal -> {
            FiveColumnCollage(
                covers = selectedCovers
            )
        }
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
fun PreviewTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
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
                .size(25.dp)
                .clickable {
                    //TODO Implements savable image
                }
        )
    }
}

private fun saveBitmapToFile(context: Context, view: View, onFileSaved: (File) -> Unit) {
    val bitmap = view.drawToBitmap()

    val file = createTempImageFile(context)
    val outputStream: OutputStream = FileOutputStream(file)

    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    outputStream.close()

    onFileSaved.invoke(file)
}

private fun createTempImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}