package com.wachon.spotiwrap.core.design.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.Title

@Composable
fun InfoBox(
    data: Int,
    dataName: String,
    modifier: Modifier = Modifier,
) {
    SquareBox(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {

            TextNoPadding(
                text = data.toString(),
                style = Title.copy(fontSize = 40.sp),
                color = BubblegumPink
            )

            TextNoPadding(
                text = dataName,
                style = SmallTitle.copy(fontSize = 24.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

        }
    }

}

@Composable
fun FitInfoBoxText(
    data: String,
    dataName: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15))
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {

            TextNoPadding(
                text = data,
                style = SmallTitle.copy(fontSize = 20.sp),
                color = BubblegumPink
            )

            TextNoPadding(
                text = dataName,
                style = SmallTitle.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

        }
    }
}

@Composable
fun FitInfoBoxGraph(
    data: Int,
    dataName: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15))
            .background(MaterialTheme.colorScheme.surface)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {

            var animateWidth by rememberSaveable {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = Unit) {
                if (animateWidth) return@LaunchedEffect
                animateWidth = true
            }

            val width by animateFloatAsState(
                (if (animateWidth) (data.toFloat() / 100) else 0f), label = "",
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            )

            BoxWithConstraints(
                Modifier
                    .clip(RoundedCornerShape(30))
                    .height(28.dp)
                    .fillMaxWidth()
                    .background(Color.Black)
            ) {
                Box(
                    modifier = Modifier
                        .animateContentSize()
                        .clip(RoundedCornerShape(30))
                        .height(28.dp)
                        .fillMaxWidth(fraction = width)
                        .background(color = MaterialTheme.colorScheme.primary)
                )

                TextNoPadding(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .align(Alignment.CenterStart),
                    text = data.toString(),
                    style = SmallTitle.copy(fontSize = 18.sp),
                )
            }

            TextNoPadding(
                text = dataName,
                style = SmallTitle.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}