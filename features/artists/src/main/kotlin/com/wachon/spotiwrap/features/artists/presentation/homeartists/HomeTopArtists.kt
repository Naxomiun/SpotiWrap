package com.wachon.spotiwrap.features.artists.presentation.homeartists

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wachon.spotiwrap.core.design.components.TextWithLine
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.features.artists.presentation.common.ArtistItem
import com.wachon.spotiwrap.features.artists.presentation.model.ArtistUI
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun HomeTopArtists(
    modifier: Modifier = Modifier,
    artists: ImmutableList<ArtistUI>,
    onArtistSelected: (String) -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        HeaderTopArtistList()
        Spacer(modifier = Modifier.height(8.dp))
        TopArtistList(
            artists = artists,
            onArtistSelected = onArtistSelected
        )
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
        TextWithLine(
            text = "Top Artists"
        )
    }

}

@Composable
fun TopArtistList(
    modifier: Modifier = Modifier,
    artists: ImmutableList<ArtistUI>,
    onArtistSelected: (String) -> Unit,
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
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onArtistSelected.invoke(artist.artistId) },
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
        ) {}
    }
}