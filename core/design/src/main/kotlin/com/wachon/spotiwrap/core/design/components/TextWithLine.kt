package com.wachon.spotiwrap.core.design.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.wachon.spotiwrap.core.design.theme.Body

@Composable
fun TextWithLine(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = Body.copy(fontWeight = FontWeight.W700)
) {

    val primaryColor = MaterialTheme.colorScheme.primary
    Text(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = primaryColor,
                    start = Offset(-20f, size.height / 1.6f),
                    end = Offset(size.width / 1.1F, size.height / 1.6f),
                    strokeWidth = size.height / 4
                )
            },
        text = text,
        style = textStyle
    )

}