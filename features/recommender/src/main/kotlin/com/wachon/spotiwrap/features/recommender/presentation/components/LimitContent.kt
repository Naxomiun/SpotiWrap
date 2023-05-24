package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.components.TextWithLine

@Composable
fun LimitContent(
    modifier: Modifier = Modifier,
    limit: MutableState<Float>
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.height(16.dp))
        LimitTitle()
        Spacer(modifier = Modifier.height(8.dp))
        LimitBar(limit = limit)
    }
}

@Composable
fun LimitTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        TextWithLine(text = "Limit")
    }
}

@Composable
fun LimitBar(
    modifier: Modifier = Modifier,
    limit: MutableState<Float>
) {
    val step = 5
    val minValue = 10
    val maxValue = 50

    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Text(text = limit.value.toInt().toString())

        Slider(
            value = limit.value,
            onValueChange = { newValue ->
                val roundedValue = (newValue / step).toInt() * step
                limit.value = roundedValue.toFloat()
            },
            valueRange = minValue.toFloat()..maxValue.toFloat(),
            steps = (maxValue - minValue) / step,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}