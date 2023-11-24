package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.theme.Title

@Composable
fun ScreenTitle(text: String) {
    Text(
        modifier = Modifier.padding(top = 24.dp, start = 24.dp),
        text = text,
        style = Title,
        color = MaterialTheme.colorScheme.onBackground,
    )
}