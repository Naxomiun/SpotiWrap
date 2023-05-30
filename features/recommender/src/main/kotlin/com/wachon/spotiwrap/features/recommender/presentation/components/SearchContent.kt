package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.common.extensions.capitalizeFirst
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.common.model.TopItemType
import com.wachon.spotiwrap.core.common.model.TrackModel
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.SubBody

@Composable
fun <T> SearchContent(
    modifier: Modifier = Modifier,
    type: TopItemType,
    query: String,
    seeds: List<T>,
    suggestions: List<T>,
    onQueryChanged: (String) -> Unit,
    onSeedAdded: (T) -> Unit,
    onSeedRemoved: (T) -> Unit,
    onSuggestionClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTitle(title = type.name.capitalizeFirst())
        Spacer(modifier = Modifier.height(8.dp))
        SearchInput(
            type = type,
            query = query,
            seeds = seeds,
            suggestions = suggestions,
            onQueryChanged = onQueryChanged,
            onSeedAdded = onSeedAdded,
            onSeedRemoved = onSeedRemoved,
            onSuggestionClicked = onSuggestionClicked
        )
    }
}

@Composable
fun SearchTitle(
    modifier: Modifier = Modifier,
    title: String,
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        TextWithLine(text = title)
    }
}

@Composable
fun <T> SearchInput(
    modifier: Modifier = Modifier,
    type: TopItemType,
    query: String,
    seeds: List<T>,
    suggestions: List<T>,
    onQueryChanged: (String) -> Unit,
    onSeedAdded: (T) -> Unit,
    onSeedRemoved: (T) -> Unit,
    onSuggestionClicked: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        TextField(
            value = query,
            onValueChange = {
                onQueryChanged.invoke(it)
            },
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp),
            leadingIcon = {
                Icon(
                    imageVector = if (type == TopItemType.ARTISTS) Filled.Person else Filled.PlayArrow,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { onQueryChanged.invoke("") }
                    ) {
                        Icon(
                            imageVector = Filled.Clear,
                            contentDescription = "Clear Icon",
                            tint = Color.Gray
                        )
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (query.isNotEmpty()) {
                        onSeedAdded.invoke(suggestions.first())
                        onQueryChanged.invoke("")
                    }
                }
            )
        )
    }

    suggestions.forEach { suggestion ->
        SuggestionItem(
            item = suggestion,
            onItemClick = {
                onSeedAdded.invoke(suggestion)
                onQueryChanged.invoke("")
                onSuggestionClicked.invoke()
            }
        )
    }

    TagsList(
        items = seeds,
        onRemove = { seed -> onSeedRemoved.invoke(seed) }
    )
}

@Composable
fun <T> SuggestionItem(item: T, onItemClick: () -> Unit) {
    val suggestionName: String = when (item) {
        is ArtistModel -> item.name
        is TrackModel -> item.title
        else -> ""
    }

    Text(
        text = suggestionName,
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { onItemClick() }
            .padding(vertical = 8.dp)
    )
}

@Composable
fun <T> TagsList(
    items: List<T>,
    onRemove: (T) -> Unit,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(items) { item ->
            val tagName: String = when (item) {
                is ArtistModel -> item.name
                is TrackModel -> item.title
                else -> ""
            }
            TagItem(tagName = tagName, onRemove = { onRemove(item) })
        }
    }
}

@Composable
fun TagItem(tagName: String, onRemove: () -> Unit) {
    Surface(
        modifier = Modifier.padding(4.dp),
        color = Color.Black,
        shape = MaterialTheme.shapes.small.copy(CornerSize(45.dp)),
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.padding(start = 14.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = tagName,
                style = SubBody,
                modifier = Modifier.padding(end = 0.dp)
            )
            IconButton(onClick = onRemove) {
                Icon(Filled.Clear, contentDescription = "Clear Icon")
            }
        }
    }
}
