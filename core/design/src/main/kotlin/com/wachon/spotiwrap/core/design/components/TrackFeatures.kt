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
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wachon.spotiwrap.core.common.model.TrackFeaturesModel
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.SpotifyBlack

@Composable
fun TrackFeatures(features: TrackFeaturesModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Column(
            modifier = Modifier.weight(1F)
        ) {
            FeatureItem(name = "acousticness", value = features.acousticness)
            FeatureItem(name = "danceability", value = features.danceability)
            FeatureItem(name = "energy", value = features.energy)
        }

        Column(
            modifier = Modifier.weight(1F)
        ) {
            FeatureItem(name = "instrumentalness", value = features.instrumentalness)
            FeatureItem(name = "liveness", value = features.liveness)
            FeatureItem(name = "speechiness", value = features.speechiness)
        }

    }
}

@Composable
fun FeatureItem(name: String, value: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            var animateWidth by rememberSaveable {
                mutableStateOf(false)
            }

            LaunchedEffect(key1 = Unit) {
                if (animateWidth) return@LaunchedEffect
                animateWidth = true
            }

            val width by animateFloatAsState(
                (if (animateWidth) value else 0f), label = "",
                animationSpec = tween(
                    durationMillis = 1000,
                    delayMillis = 100,
                    easing = LinearOutSlowInEasing
                )
            )

            TextNoPadding(
                text = name,
                style = SmallTitle.copy(fontSize = 16.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

            BoxWithConstraints(
                Modifier
                    .padding(top = 4.dp)
                    .clip(RoundedCornerShape(30))
                    .height(8.dp)
                    .fillMaxWidth()
                    .background(SpotifyBlack)
            ) {
                Box(
                    modifier = Modifier
                        .animateContentSize()
                        .clip(RoundedCornerShape(30))
                        .height(8.dp)
                        .fillMaxWidth(fraction = width)
                        .background(color = MaterialTheme.colorScheme.primary)
                        .align(Alignment.CenterStart),
                )
            }
        }
    }
}

@Preview
@Composable
fun TrackFeaturesPreview() {
    SpotiWrapTheme {
        TrackFeatures(
            features = TrackFeaturesModel(
                acousticness = 0.00242F,
                danceability = 0.585F,
                energy = 0.842F,
                instrumentalness = 0.00686F,
                liveness = 0.0866F,
                speechiness = 0.0556F,
            )
        )
    }
}