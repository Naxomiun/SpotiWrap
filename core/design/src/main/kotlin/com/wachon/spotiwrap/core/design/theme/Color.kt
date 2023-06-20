package com.wachon.spotiwrap.core.design.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val BubblegumPink = Color(0xFFFD5D8A)
val SpotifyGreen = Color(0xFF1DB954)
val SpotifyBlack = Color(0xFF191414)

val blurryBrush1 = Brush.verticalGradient(colors = listOf(BubblegumPink, Color.Black))
val blurryBrush2 = Brush.horizontalGradient(colors = listOf(Color.Black, BubblegumPink))
val blurryBrush3 = Brush.horizontalGradient(colors = listOf(BubblegumPink, Color.Black))