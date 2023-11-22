package com.wachon.spotiwrap.features.collage.presentation.components

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.features.collage.presentation.utils.BitmapUtil

@Composable
fun ColumnCollage(
    columns: Int,
    covers: List<String>,
    onBitmapCreated: (Bitmap) -> Unit
) {
    val view = LocalView.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .height(400.dp)
            .onGloballyPositioned {
                BitmapUtil.createBitmapFromCompose(
                    context = context,
                    view = view,
                    layoutCoordinates = it,
                    onBitmapCreated = onBitmapCreated
                )
            },
    ) {
        GridCollage(cells = columns, covers = covers)
    }
}
