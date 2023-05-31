package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.components.TextWithLine

@Composable
fun PlaylistNameContent(
    modifier: Modifier = Modifier,
    title: String,
    name: String,
    onQueryChanged: (String) -> Unit,
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        NameTitle(title = title)
        Spacer(modifier = Modifier.height(8.dp))
        NameInput(name = name, onNameChanged = onQueryChanged)
    }


}

@Composable
fun NameTitle(
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
fun NameInput(
    modifier: Modifier = Modifier,
    name: String,
    onNameChanged: (String) -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp)
    ) {
        TextField(
            value = name,
            onValueChange = {
                onNameChanged.invoke(it)
            },
            modifier = modifier
                .weight(1f)
                .padding(end = 8.dp),
            trailingIcon = {
                if (name.isNotEmpty()) {
                    IconButton(
                        onClick = { onNameChanged.invoke("") }
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
        )
    }
}