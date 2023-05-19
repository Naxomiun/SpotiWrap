package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.common.model.ItemFame.NONE

@Composable
fun TextGradientFame(
    text: String,
    textStyle: TextStyle,
    fame: ItemFame,
    gradientStart: Float,
    gradientEnd: Float
) {
    val hasToHideFame = (fame == NONE)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        TextNoPadding(
            text = text,
            style = textStyle,
            maxLines = 1,
            softWrap = hasToHideFame,
            overflow = if (hasToHideFame) TextOverflow.Ellipsis else TextOverflow.Clip
        )
        if (!hasToHideFame) {
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color.Transparent, Color.Black),
                            startX = gradientStart,
                            endX = gradientEnd
                        )
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painterResource(id = fame.drawableResId),
                        contentDescription = fame.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(10.dp)
                    )
                }
            }
        }
    }
}