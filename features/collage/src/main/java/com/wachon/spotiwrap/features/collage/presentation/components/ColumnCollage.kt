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
fun ThreeColumnCollage(
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
        GridCollage(cells = 3, covers = covers.take(9))
    }
}

@Composable
fun FourColumnCollage(
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
        GridCollage(cells = 4, covers = covers.take(16))
    }
}

@Composable
fun FiveColumnCollage(
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
        GridCollage(cells = 5, covers = covers.take(25))
    }
}