package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun SquareBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    roundedCornersPercent: Int = 15,
    content: @Composable () -> Unit,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(roundedCornersPercent))
            .background(backgroundColor)
            .aspectRatio(1f)
    ) {
        content()
    }

}