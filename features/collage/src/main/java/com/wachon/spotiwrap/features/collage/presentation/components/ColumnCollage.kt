package com.wachon.spotiwrap.features.collage.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThreeColumnCollage(covers: List<String>) {
    Column(modifier = Modifier.height(400.dp)) {
        GridCollage(cells = 3, covers = covers.take(9))
    }
}

@Composable
fun FourColumnCollage(covers: List<String>) {
    Column(modifier = Modifier.height(400.dp)) {
        GridCollage(cells = 4, covers = covers.take(16))
    }
}

@Composable
fun FiveColumnCollage(covers: List<String>) {
    Column(modifier = Modifier.height(400.dp)) {
        GridCollage(cells = 5, covers = covers.take(25))
    }
}