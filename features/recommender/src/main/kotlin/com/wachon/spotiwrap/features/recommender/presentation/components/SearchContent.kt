package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.common.model.ArtistModel
import com.wachon.spotiwrap.core.design.components.TextWithLine

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    query: String,
    seeds: List<ArtistModel>,
    suggestions: List<ArtistModel>,
    onQueryChanged: (String) -> Unit,
    onSeedAdded: (ArtistModel) -> Unit,
    onSeedRemoved: (ArtistModel) -> Unit,
    onSuggestionClicked: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        SearchTitle()
        Spacer(modifier = Modifier.height(8.dp))
        SearchInput(
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
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        TextWithLine(text = "Artists")
    }
}

@Composable
fun SearchInput(
    modifier: Modifier = Modifier,
    query: String,
    seeds: List<ArtistModel>,
    suggestions: List<ArtistModel>,
    onQueryChanged: (String) -> Unit,
    onSeedAdded: (ArtistModel) -> Unit,
    onSeedRemoved: (ArtistModel) -> Unit,
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
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Search Icon"
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(
                        onClick = { onQueryChanged.invoke("") }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
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
            text = suggestion.name,
            onItemClick = {
                onSeedAdded.invoke(suggestion)
                onQueryChanged.invoke("")
                onSuggestionClicked.invoke()
            }
        )
    }

    seeds.forEach { seed ->
        Tag(
            text = seed.name,
            onRemove = { onSeedRemoved.invoke(seed) }
        )
    }
}

@Composable
fun SuggestionItem(text: String, onItemClick: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .padding(start = 16.dp)
            .clickable { onItemClick() }
            .padding(vertical = 8.dp)
    )
}

@Composable
fun Tag(text: String, onRemove: () -> Unit) {
    Surface(
        modifier = Modifier.padding(4.dp),
        color = MaterialTheme.colorScheme.primary,
        contentColor = contentColorFor(MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = text, modifier = Modifier.padding(end = 8.dp))
            IconButton(onClick = onRemove) {
                Icon(Icons.Filled.Clear, contentDescription = "Clear Icon")
            }
        }
    }
}
