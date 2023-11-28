package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.ui.TopItemUI

@Composable
fun TopList(listState: LazyListState, content: List<TopItemUI>) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(4.dp),
    ) {
        itemsIndexed(content) { index, item ->
            TopItem(index = index + 1, item = item)
        }
    }
}