package com.wachon.spotiwrap.features.collage.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.common.extensions.capitalizeFirst
import com.wachon.spotiwrap.core.common.extensions.toTime
import com.wachon.spotiwrap.core.common.model.TopItemTimeRange
import com.wachon.spotiwrap.core.design.components.SingleChoiceButton
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum
import com.wachon.spotiwrap.features.collage.presentation.CollageOptionsEnum.TOP
import com.wachon.spotiwrap.features.collage.presentation.CollageSizesEnum
import com.wachon.spotiwrap.features.collage.presentation.CollageTypesEnum

@Composable
fun CollageHeader(
    timeIndex: Int,
    time: List<TopItemTimeRange>,
    optionIndex: Int,
    options: List<CollageOptionsEnum>,
    typeIndex: Int,
    types: List<CollageTypesEnum>,
    sizeIndex: Int,
    sizes: List<CollageSizesEnum>,
    onTimeSelect: (Int) -> Unit,
    onOptionSelect: (Int) -> Unit,
    onTypeSelect: (Int) -> Unit,
    onSizeSelect: (Int) -> Unit,

    ) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SingleChoiceButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            selected = timeIndex,
            options = time.map { it.toTime() },
            onSelect = onTimeSelect
        )
        SingleChoiceButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            selected = optionIndex,
            options = options.map { it.name.capitalizeFirst() },
            onSelect = onOptionSelect
        )
        if (optionIndex != TOP.ordinal) {
            SingleChoiceButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                selected = typeIndex,
                options = types.map { it.name.capitalizeFirst() },
                onSelect = onTypeSelect
            )
            SingleChoiceButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
                selected = sizeIndex,
                options = sizes.map { it.name.capitalizeFirst() },
                onSelect = onSizeSelect
            )
        }
    }
}