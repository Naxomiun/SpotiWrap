package com.wachon.spotiwrap.features.artists.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.common.model.ItemFame
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI

@Composable
fun ArtistItem(
    modifier: Modifier = Modifier, artist: ArtistUI
) {
    Column(
        modifier = modifier.width(80.dp)
    ) {
        AsyncImage(
            model = artist.artistImage,
            contentScale = ContentScale.Crop,
            contentDescription = artist.artistName,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            TextNoPadding(
                text = artist.artistName,
                style = Body.copy(fontWeight = FontWeight.W600),
                maxLines = 1,
                softWrap = false,
                overflow = TextOverflow.Clip
            )
            Box(
                modifier = Modifier
                    .height(25.dp)
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color.Transparent, Color.Black), startX = 120f, endX = 185f
                        )
                    )
            ) {
                if (artist.artistFame != ItemFame.NONE) {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Image(
                            when (artist.artistFame) {
                                ItemFame.UP -> painterResource(R.drawable.fame_up)
                                ItemFame.EVEN -> painterResource(R.drawable.fame_even)
                                ItemFame.DOWN -> painterResource(R.drawable.fame_down)
                                ItemFame.NEW -> painterResource(R.drawable.fame_new)
                                ItemFame.NONE -> TODO()
                            },
                            contentDescription = artist.artistFame.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(10.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ArtistItem() {
    SpotiWrapTheme {
        ArtistItem(
            artist = ArtistUI(
                artistId = "1",
                artistFame = ItemFame.UP,
                artistName = "Machine Gun Kelly",
                artistImage = "https://i.scdn.co/image/ab6761610000e5ebd7b7b6b4b2b9b2b9b2b9b2b9"
            )
        )
    }
}