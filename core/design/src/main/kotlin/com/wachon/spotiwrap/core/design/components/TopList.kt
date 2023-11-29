package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.ui.TopItemUI

@Composable
fun TopList(listState: LazyListState, content: List<TopItemUI>, onItemClicked: (String) -> Unit) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(4.dp),
    ) {
        itemsIndexed(content) { index, item ->
            Row(modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onItemClicked.invoke(item.id) }) {
                TopItem(index = index + 1, item = item)
            }
        }
    }
}