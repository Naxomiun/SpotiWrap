package com.wachon.spotiwrap.core.design.extensions

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils

fun @receiver:ColorInt Int.isDark(): Boolean =
    ColorUtils.calculateLuminance(this) < 0.5
