package com.wachon.spotiwrap.features.artists.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.model.ItemFame.UP
import com.wachon.spotiwrap.core.design.components.TextGradientFame
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI

@Composable
fun ArtistItem(
    modifier: Modifier = Modifier, artist: ArtistUI
) {
    Column(
        modifier = modifier.width(100.dp)
    ) {
        AsyncImage(
            model = artist.artistImage,
            contentScale = ContentScale.Crop,
            contentDescription = artist.artistName,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextGradientFame(
            text = artist.artistName,
            textStyle = Body.copy(fontWeight = FontWeight.W600),
            fame = artist.artistFame,
            gradientStart = 120f,
            gradientEnd = 185f
        )
    }
}

@Preview
@Composable
private fun ArtistItem() {
    SpotiWrapTheme {
        ArtistItem(
            artist = ArtistUI(
                artistId = "1",
                artistFame = UP,
                artistName = "Machine Gun Kelly",
                artistImage = "https://i.scdn.co/image/ab6761610000e5ebd7b7b6b4b2b9b2b9b2b9b2b9",
                artistFollowers = 0,
                artistPopularity = 0,
                artistExternalUrl = ""
            )
        )
    }
}