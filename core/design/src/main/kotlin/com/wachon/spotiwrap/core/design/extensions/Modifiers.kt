package com.wachon.spotiwrap.core.design.extensions

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze

fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: (Modifier.() -> Modifier)? = null
): Modifier {
    return if (condition) {
        then(ifTrue(Modifier))
    } else if (ifFalse != null) {
        then(ifFalse(Modifier))
    } else {
        this
    }
}

@Stable
fun Modifier.customHaze(
    hazeStateProvider: () -> HazeState,
    backgroundColor: Color,
): Modifier {
    
    val alphaChannel = if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.S) {
        1f
    } else {
        0.2f
    }

    return this.haze(
        hazeStateProvider(),
        backgroundColor = backgroundColor,
        tint = backgroundColor.copy(alpha = alphaChannel),
        blurRadius = 20.dp,
    )
}