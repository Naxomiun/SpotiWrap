package com.wachon.spotiwrap.features.tracks.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.features.tracks.presentation.model.TrackUI

@Composable
fun TrackItem(
    modifier: Modifier = Modifier,
    track: TrackUI
) {

    Column(
        modifier = modifier
            .width(100.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .size(100.dp),
            imageModel = { track.trackImage },
            imageOptions = ImageOptions(
                contentDescription = track.trackTitle,
                contentScale = ContentScale.Crop
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextNoPadding(
            text = track.trackTitle,
            style = Body.copy(fontWeight = FontWeight.W600),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        TextNoPadding(
            text = track.trackArtist,
            style = SubBody.copy(fontSize = 12.sp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        )
    }

}