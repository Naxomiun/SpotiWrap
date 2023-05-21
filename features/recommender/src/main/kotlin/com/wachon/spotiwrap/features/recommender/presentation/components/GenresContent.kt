package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SubBody

@Composable
fun GenresContent(
    modifier: Modifier = Modifier,
    genres: List<String>,
    rowsPerColumn: Int,
    checkedList: SnapshotStateList<String>,
) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        GenresTitle()
        Spacer(modifier = Modifier.height(8.dp))
        GenresRow(genres = genres, rowsPerColumn = rowsPerColumn, checkedList = checkedList)
    }
}

@Composable
fun GenresTitle(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = BubblegumPink,
                        start = Offset(-20f, size.height / 1.6f),
                        end = Offset(size.width / 1.1F, size.height / 1.6f),
                        strokeWidth = size.height / 4
                    )
                },
            text = "Genres",
            style = Body.copy(fontWeight = FontWeight.W700)
        )
    }
}

@Composable
fun GenresRow(genres: List<String>, rowsPerColumn: Int, checkedList: SnapshotStateList<String>) {
    val genresChildColumns = genres.chunked(rowsPerColumn)

    LazyRow(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = genresChildColumns,
            key = { it }
        ) { column ->
            GenresChildColumns(
                genres = column,
                checkedList = checkedList
            )
        }
    }
}

@Composable
fun GenresChildColumns(
    genres: List<String>,
    checkedList: SnapshotStateList<String>
) {
    Column(
        modifier = Modifier
            .width(IntrinsicSize.Max)
            .horizontalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        genres.forEach {
            GenresChildRow(
                name = it,
                isChecked = checkedList.contains(it),
                onCheckedChange = { isChecked ->
                    if (isChecked) {
                        checkedList.add(it)
                    } else {
                        checkedList.remove(it)
                    }
                }
            )
        }
    }
}

@Composable
fun GenresChildRow(
    name: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val animatedProgress = remember { Animatable(if (isChecked) 1f else 0f) }

    Row {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(3.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onCheckedChange(!isChecked) }
                .drawBehind {
                    val progress = animatedProgress.value
                    val lineStartX = getInterpolation(0f, 0f, progress, fillWidth = isChecked)
                    val lineEndX = getInterpolation(size.height, size.width, progress, fillWidth = isChecked)

                    val lineStartXNotChecked = getInterpolation(0f, size.width, progress, fillWidth = isChecked)
                    val lineEndXNotChecked = getInterpolation(0f, 0f, progress, fillWidth = isChecked)

                    if (isChecked) {
                        drawLine(
                            color = primaryColor,
                            start = Offset(lineStartX, size.height / 1.4f),
                            end = Offset(lineEndX, size.height / 1.4f),
                            strokeWidth = size.height / 4
                        )
                    } else {
                        drawLine(
                            color = primaryColor,
                            start = Offset(lineStartXNotChecked, size.height / 1.4f),
                            end = Offset(lineEndXNotChecked, size.height / 1.4f),
                            strokeWidth = size.height / 4
                        )
                    }
                },
            text = name,
            style = SubBody
        )
    }

    LaunchedEffect(isChecked) {
        animatedProgress.animateTo(if (isChecked) 1f else 0f)
    }
}

fun getInterpolation(startValue: Float, endValue: Float, fraction: Float, fillWidth: Boolean): Float {
    val interpolatedValue = startValue + fraction * (endValue - startValue)
    return if (fillWidth) {
        interpolatedValue.coerceIn(startValue, endValue)
    } else {
        interpolatedValue
    }
}
