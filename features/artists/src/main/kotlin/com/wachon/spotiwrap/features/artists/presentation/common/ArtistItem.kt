package com.wachon.spotiwrap.features.artists.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI

@Composable
fun ArtistItem(
    modifier: Modifier = Modifier,
    artist: ArtistUI
) {
    Column(
        modifier = modifier
            .width(80.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
            imageModel = { artist.artistImage },
            imageOptions = ImageOptions(
                contentDescription = artist.artistName,
                contentScale = ContentScale.Crop
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextNoPadding(
            text = artist.artistName,
            style = Body.copy(fontWeight = FontWeight.W600),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun ArtistItem() {
    SpotiWrapTheme {
        ArtistItem(
            artist = ArtistUI(
                artistId = "1",
                artistName = "Machine Gun Kelly",
                artistImage = "https://i.scdn.co/image/ab6761610000e5ebd7b7b6b4b2b9b2b9b2b9b2b9"
            )
        )
    }
}