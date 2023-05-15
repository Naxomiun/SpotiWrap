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
import com.wachon.spotiwrap.core.common.model.ItemFame.DOWN
import com.wachon.spotiwrap.core.common.model.ItemFame.EVEN
import com.wachon.spotiwrap.core.common.model.ItemFame.NEW
import com.wachon.spotiwrap.core.common.model.ItemFame.NONE
import com.wachon.spotiwrap.core.common.model.ItemFame.UP

@Composable
fun TextGradientFame(
    text: String,
    textStyle: TextStyle,
    fame: ItemFame,
    gradientStart: Float,
    gradientEnd: Float
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(20.dp)
    ) {
        TextNoPadding(
            text = text,
            style = textStyle,
            maxLines = 1,
            softWrap = fame == NONE,
            overflow = if (fame == NONE) TextOverflow.Ellipsis else TextOverflow.Clip
        )
        if (fame != NONE) {
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
                        when (fame) {
                            UP -> painterResource(UP.drawableResId)
                            EVEN -> painterResource(EVEN.drawableResId)
                            DOWN -> painterResource(DOWN.drawableResId)
                            NEW -> painterResource(NEW.drawableResId)
                            else -> {
                                painterResource(NONE.drawableResId)
                            }
                        },
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