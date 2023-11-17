package com.wachon.spotiwrap.features.recommender.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.PlaylistModel
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SubBody

@Composable
fun PlaylistItem(
    modifier: Modifier = Modifier,
    playlist: PlaylistModel,
    isChecked: Boolean,
    onPlaylistClicked: (Boolean) -> Unit,
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val animatedProgress = remember { Animatable(if (isChecked) 1f else 0f) }

    Row(
        modifier = modifier
            .width(300.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) { onPlaylistClicked(!isChecked) }
    ) {
        AsyncImage(
            model = playlist.imageUrl,
            contentDescription = playlist.name,
            modifier = Modifier
                .size(100.dp)
                .clip(MaterialTheme.shapes.small)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, start = 8.dp)
                    .drawBehind {
                        val progress = animatedProgress.value
                        val lineStartX = getInterpolation(0f, 0f, progress, fillWidth = isChecked)
                        val lineEndX = getInterpolation(
                            size.height,
                            size.width,
                            progress,
                            fillWidth = isChecked
                        )

                        val lineStartXNotChecked =
                            getInterpolation(0f, size.width, progress, fillWidth = isChecked)
                        val lineEndXNotChecked =
                            getInterpolation(0f, 0f, progress, fillWidth = isChecked)

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
                text = playlist.name,
                style = Body.copy(fontSize = 16.sp),
                overflow = TextOverflow.Visible,
                color = MaterialTheme.colorScheme.onBackground,
            )
            LaunchedEffect(isChecked) {
                animatedProgress.animateTo(if (isChecked) 1f else 0f)
            }
            TextNoPadding(
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
                text = playlist.description,
                style = SubBody.copy(fontSize = 12.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }
    }
}

fun getInterpolation(
    startValue: Float,
    endValue: Float,
    fraction: Float,
    fillWidth: Boolean
): Float {
    val interpolatedValue = startValue + fraction * (endValue - startValue)
    return if (fillWidth) {
        interpolatedValue.coerceIn(startValue, endValue)
    } else {
        interpolatedValue
    }
}