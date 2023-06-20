package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.design.extensions.conditional

@Composable
fun ProfileUserImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    size: Dp = 65.dp,
    borderWidth: Dp = 0.dp,
) {

    AsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .size(size)
            .border(
                width = borderWidth,
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
            .conditional(
                condition = borderWidth > 0.dp,
                ifTrue = { padding(6.dp) }
            )
            .clip(CircleShape),
    )

}
