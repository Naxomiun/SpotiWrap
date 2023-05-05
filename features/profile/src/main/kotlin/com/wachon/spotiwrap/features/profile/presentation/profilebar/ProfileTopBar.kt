package com.wachon.spotiwrap.features.profile.presentation.profilebar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI

@Composable
fun ProfileTopBar(
    user: UserUI?,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Rebugger(
        trackMap = mapOf(
            "user" to user
        )
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = paddingValues.calculateTopPadding() + 24.dp,
                bottom = 24.dp
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ProfileGreetings { user?.displayName ?: "" }
        ProfileImage { user?.image ?: "" }
    }
}

@Composable
fun ProfileGreetings(
    modifier: Modifier = Modifier,
    username: () -> String,
) {

    Column(
        modifier = modifier
    ) {
        TextNoPadding(
            text = "Good evening!",
            style = Title,
            color = MaterialTheme.colorScheme.onBackground,
        )
        TextNoPadding(
            text = username(),
            style = Body,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
        )

    }
}

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: () -> String
) {

    CoilImage(
        modifier = modifier
            .size(55.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            )
            .padding(6.dp)
            .clip(CircleShape),
        imageModel = imageUrl,
        imageOptions = ImageOptions(
            contentScale = ContentScale.Crop
        ),
        component = rememberImageComponent {
            +CrossfadePlugin()
        }
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewProfileTopBar() {
    SpotiWrapTheme {
        ProfileTopBar(
            user = UserUI(
                displayName = "Wachon",
                image = "https://i.scdn.co/image/ab6775700000ee85f0b0b5e2a5a0a0b0c0a0b0b0",
                country = "ES",
                email = "nacho@spotify.com"
            )
        )
    }
}