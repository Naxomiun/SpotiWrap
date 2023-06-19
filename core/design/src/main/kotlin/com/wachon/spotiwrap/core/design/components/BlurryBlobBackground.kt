package com.wachon.spotiwrap.core.design.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.theme.blurryBrush1
import com.wachon.spotiwrap.core.design.theme.blurryBrush2
import com.wachon.spotiwrap.core.design.theme.blurryBrush3

@Composable
fun BlurryBlobBackground() {
    val infinite = rememberInfiniteTransition(label = "infinite transition")

    val circle1TranslationDuration = 24000
    val circle1XMult = infinite.animateFloat(
        initialValue = .25f,
        targetValue = .25f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = circle1TranslationDuration
                .5f at (circle1TranslationDuration * .25).toInt() with LinearEasing
                1.0f at (circle1TranslationDuration * .5).toInt() with LinearEasing
                .5f at (circle1TranslationDuration * .75).toInt() with LinearEasing
                .25f at circle1TranslationDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle1XMult"
    )
    val circle1YMult = infinite.animateFloat(
        initialValue = .25f, targetValue = .25f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = circle1TranslationDuration
                .5f at (circle1TranslationDuration * .25).toInt() with LinearEasing
                .5f at (circle1TranslationDuration * .5).toInt() with LinearEasing
                1.0f at (circle1TranslationDuration * .75).toInt() with LinearEasing
                .25f at circle1TranslationDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle1YMult"
    )
    val circle1ScaleDuration = 13456
    val circle1Scale = infinite.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = circle1ScaleDuration
                0.8f at (circle1ScaleDuration * .33).toInt() with LinearEasing
                1.2f at (circle1ScaleDuration * .66).toInt() with LinearEasing
                1.0f at circle1ScaleDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle1Scale"
    )


    val circle3TranslationDuration = 10000
    val circle3XMult = infinite.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = circle3TranslationDuration
                0f at (circle3TranslationDuration * 0.25).toInt() with LinearEasing
                1.0f at circle3TranslationDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle3XMult"
    )
    val circle3YMult = infinite.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = circle3TranslationDuration
                0f at (circle3TranslationDuration * 0.5).toInt() with LinearEasing
                1.0f at circle3TranslationDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle3YMult"
    )
    val circle3ScaleDuration = 9875
    val circle3Scale = infinite.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = circle3ScaleDuration
                1.1f at (circle3ScaleDuration * .33).toInt() with LinearEasing
                0.85f at (circle3ScaleDuration * .66).toInt() with LinearEasing
                1.0f at circle3ScaleDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle3Scale"
    )

    val circle2TranslationDuration = 15000
    val circle2YMult = infinite.animateFloat(
        initialValue = 0.5f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = circle2TranslationDuration
                0f at (circle2TranslationDuration * .25).toInt() with LinearEasing
                .5f at (circle2TranslationDuration * .5).toInt() with LinearEasing
                1.0f at (circle2TranslationDuration * .75).toInt() with LinearEasing
                .5f at circle2TranslationDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle2YMult"
    )
    val circle2ScaleDuration = 16235
    val circle2Scale = infinite.animateFloat(
        initialValue = 1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            keyframes {
                durationMillis = circle2ScaleDuration
                1.2f at (circle2ScaleDuration * .33).toInt() with LinearEasing
                1.1f at (circle2ScaleDuration * .66).toInt() with LinearEasing
                1.0f at circle2ScaleDuration with LinearEasing
            },
            repeatMode = RepeatMode.Restart
        ), label = "circle2Scale"
    )

    Box(
        modifier = Modifier
            .size(120.dp)
            .background(color = MaterialTheme.colorScheme.background)
            .blur(radius = 100.dp, edgeTreatment = BlurredEdgeTreatment.Unbounded)
            .drawBehind {

                val offset1 = Offset(
                    size.width * circle1XMult.value,
                    size.height * circle1YMult.value,
                )
                drawCircle(
                    brush = blurryBrush1,
                    radius = size.minDimension * .75f * circle1Scale.value,
                    center = offset1,
                    alpha = 0.75f
                )
                val offset2 = Offset(
                    size.width * .5f,
                    size.height * circle2YMult.value
                )
                drawCircle(
                    brush = blurryBrush2,
                    radius = size.minDimension * .75f * circle2Scale.value,
                    center = offset2,
                    alpha = 0.75f
                )

                val offset3 = Offset(
                    size.width * circle3XMult.value,
                    size.height * circle3YMult.value,
                )

                drawCircle(
                    brush = blurryBrush3,
                    radius = size.minDimension * .75f * circle3Scale.value,
                    center = offset3,
                    alpha = 0.75f
                )
            }
    )
}