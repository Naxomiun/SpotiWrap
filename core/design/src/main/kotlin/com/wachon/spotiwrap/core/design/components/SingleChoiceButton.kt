package com.wachon.spotiwrap.core.design.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SingleChoiceButton(
    modifier: Modifier = Modifier,
    selected: Int,
    options: List<String>,
    onSelect: (Int) -> Unit
) {
    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                colors = SegmentedButtonDefaults.colors(
                    activeContainerColor = MaterialTheme.colorScheme.surface,
                    inactiveContainerColor = MaterialTheme.colorScheme.background
                ),
                onClick = {
                    onSelect.invoke(index)
                },
                selected = index == selected
            ) {
                Text(label)
            }
        }
    }
}