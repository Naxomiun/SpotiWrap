package com.wachon.spotiwrap.core.design.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.extensions.isDark

@Composable
fun BrandContainer(
    modifier: Modifier = Modifier,
    offsetSize: Float = 20F,
    backgroundColor: Color = Color.White,
    borderColor: Color = Color.Black,
    content: @Composable () -> Unit
) {

    val textColor = if (backgroundColor.toArgb().isDark()) {
        Color.White
    } else {
        Color.Black
    }

    TextColorWrapper(color = Color.Red) {
        Box(
            modifier = modifier
                .background(backgroundColor)
                .border(2.dp, borderColor, RectangleShape)
                .drawBehind {

                    val bottomPath = Path().apply {
                        moveTo(0f, size.height)
                        lineTo(offsetSize, size.height + offsetSize)
                        lineTo(size.width, size.height + offsetSize)
                        lineTo(size.width, size.height)
                        close()
                    }

                    val rightPath = Path().apply {
                        moveTo(size.width, 0f)
                        lineTo(size.width + offsetSize, offsetSize)
                        lineTo(size.width + offsetSize, size.height + offsetSize)
                        lineTo(size.width, size.height + offsetSize)
                        close()
                    }

                    drawPath(bottomPath, borderColor)
                    drawPath(rightPath, borderColor)
                }

        ) {
            TextColorWrapper(color = textColor) {
                content()
            }
        }
    }

}


@Preview
@Composable
fun PreviewBrandContainer() {
    BrandContainer(
        modifier = Modifier.padding(20.dp),
        backgroundColor = Color.White,
        borderColor = Color.Black,
        offsetSize = 20F
    ) {
        Text(text = "Hello World")
    }
}
