package com.wachon.spotiwrap.features.tracks.presentation.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    track: TrackUI
) {

    CoilImage(
        modifier = modifier.size(100.dp),
        imageModel = { track.trackImage },
        imageOptions = ImageOptions(
            contentDescription = track.trackTitle,
            contentScale = ContentScale.Crop
        )
    )

}