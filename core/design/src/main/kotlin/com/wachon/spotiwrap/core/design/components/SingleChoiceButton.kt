package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.common.extensions.toShow
import com.wachon.spotiwrap.core.common.extensions.toTime
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink

@Composable
fun SingleChoiceTypeButton(
    options: List<TopItemType>,
    onSelect: (TopItemType) -> Unit
) {
    var selectedTime by remember { mutableIntStateOf(0) }
    SingleChoiceButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        selected = selectedTime,
        options = options.map { it.toShow() },
        onSelect = { index ->
            selectedTime = index
            onSelect.invoke(options[index])
        }
    )
}

@Composable
fun SingleChoiceTimeButton(
    options: List<TopItemTimeRange>,
    onSelect: (TopItemTimeRange) -> Unit
) {
    var selectedTime by remember { mutableIntStateOf(0) }
    SingleChoiceButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 4.dp, end = 4.dp, bottom = 6.dp),
        selected = selectedTime,
        options = options.map { it.toTime() },
        onSelect = { index ->
            selectedTime = index
            onSelect.invoke(options[index])
        }
    )
}

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
                    activeContainerColor = BubblegumPink,
                    inactiveContainerColor = MaterialTheme.colorScheme.surface
                ),
                onClick = {
                    onSelect.invoke(index)
                },
                selected = index == selected
            ) {
                Text(text = label, style = Body.copy(fontSize = 12.sp))
            }
        }
    }
}