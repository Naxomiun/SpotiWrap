package com.wachon.spotiwrap.features.artists.presentation.homeartists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.artists.presentation.common.ArtistItem
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import kotlinx.collections.immutable.ImmutableList
import com.theapache64.rebugger.Rebugger
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeTopArtists(
    modifier: Modifier = Modifier,
    artists: ImmutableList<ArtistUI>
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTopArtistList()
        Spacer(modifier = Modifier.height(8.dp))
        TopArtistList(artists = artists)
    }

}

@Composable
fun HeaderTopArtistList(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .padding(horizontal = 24.dp)
    ) {
        Text(
            modifier = Modifier
                .drawBehind {
                    drawLine(
                        color = BubblegumPink,
                        start = Offset(-20f, size.height / 1.6f),
                        end = Offset(size.width / 1.1F, size.height / 1.6f),
                        strokeWidth = size.height / 4
                    )
                },
            text = "Top artists",
            style = Body.copy(fontWeight = FontWeight.W700)
        )
    }
}

@Composable
fun TopArtistList(
    modifier: Modifier = Modifier,
    artists: ImmutableList<ArtistUI>
) {

    LazyRow(
        state = rememberLazyListState(),
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = artists,
            key = { it.artistId }
        ) { artist ->
            ArtistItem(
                artist = artist
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeTopArtistsPreview() {
    SpotiWrapTheme {
        HomeTopArtists(
            artists = persistentListOf()
        )
    }
}