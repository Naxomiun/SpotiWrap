package com.wachon.spotiwrap.core.design.extensions

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import androidx.palette.graphics.Palette

fun @receiver:ColorInt Int.isDark(): Boolean =
    ColorUtils.calculateLuminance(this) < 0.5

fun Palette.getBackgroundColorFromPalette(): Color {

    this.vibrantSwatch?.let { swatch ->
        return Color(swatch.rgb)
    }

    this.lightVibrantSwatch?.let { swatch ->
        return Color(swatch.rgb)
    }

    this.darkVibrantSwatch?.let { swatch ->
        return Color(swatch.rgb)
    }

    this.dominantSwatch?.let { swatch ->
        return Color(swatch.rgb)
    }

    return Color.White
}