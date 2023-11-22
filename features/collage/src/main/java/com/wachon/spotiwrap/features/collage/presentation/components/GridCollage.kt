package com.wachon.spotiwrap.features.collage.presentation.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun GridCollage(
    cells: Int,
    covers: List<String>
) {
    LazyVerticalGrid(
        state = rememberLazyGridState(),
        userScrollEnabled = false,
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(cells)
    ) {
        items(
            items = covers
        ) { cover ->
            AsyncImage(
                model = cover,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RectangleShape)
            )
        }
    }
}