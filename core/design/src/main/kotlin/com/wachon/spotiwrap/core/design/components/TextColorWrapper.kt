package com.wachon.spotiwrap.core.design.components

import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TextColorWrapper(color: Color, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalTextStyle provides TextStyle(color = color)) {
        content()
    }
}