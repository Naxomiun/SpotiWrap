package com.wachon.spotiwrap.features.profile.presentation.profilebar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wachon.spotiwrap.core.design.R as DesignR
import com.wachon.spotiwrap.core.design.components.LottieImage
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.Body
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme
import com.wachon.spotiwrap.core.design.theme.SubBody
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.profile.presentation.model.CurrentTrackUI
import com.theapache64.rebugger.Rebugger
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI

@Composable
fun ProfileTopBar(
    user: UserUI?,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            ProfileUserName(
                modifier = Modifier.weight(1F),
                userDisplayName = user?.displayName ?: ""
            )

            ProfileUserImage(
                imageUrl = user?.image ?: ""
            )
        }

        if (user?.currentTrackUI != null) {
            ProfileCurrentSong(
                currentTrackUI = user.currentTrackUI
            )
        }
    }

}

@Composable
fun ProfileUserName(
    modifier: Modifier = Modifier,
    userDisplayName: String
) {

    TextNoPadding(
        modifier = modifier,
        text = userDisplayName,
        style = SmallTitle,
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

}

@Composable
fun ProfileUserImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {

    AsyncImage(
        model = imageUrl,
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = modifier
            .padding(start = 8.dp)
            .size(65.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.surface,
                shape = CircleShape
            )
            .padding(6.dp)
            .clip(CircleShape),
    )

}

@Composable
fun ProfileCurrentSong(
    modifier: Modifier = Modifier,
    currentTrackUI: CurrentTrackUI
) {

    Spacer(modifier = Modifier.height(8.dp))
    AnimatedVisibility(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .background(MaterialTheme.colorScheme.surface),
        visible = true,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp, top = 8.dp)
        ) {
            CurrentTrackTitle()
            Spacer(modifier = Modifier.height(4.dp))
            CurrentTrackInfo(
                trackImage = currentTrackUI.image,
                trackTitle = currentTrackUI.title,
                trackArtist = currentTrackUI.artist
            )
        }
    }

}

@Composable
fun CurrentTrackTitle(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextNoPadding(
            text = "Currenty playing",
            style = Title.copy(
                fontSize = 18.sp,
                fontWeight = FontWeight.W600
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        LottieImage(
            modifier = Modifier
                .size(30.dp),
            animation = DesignR.raw.equalizer,
            tintColor = MaterialTheme.colorScheme.primary
        )
    }

}

@Composable
fun CurrentTrackInfo(
    modifier: Modifier = Modifier,
    trackImage: String,
    trackTitle: String,
    trackArtist: String
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = trackImage,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            TextNoPadding(
                text = trackTitle,
                style = Body.copy(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            TextNoPadding(
                text = trackArtist,
                style = SubBody.copy(fontSize = 10.sp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
            )
        }
    }

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
                email = "nacho@spotify.com",
                currentTrackUI = CurrentTrackUI(
                    title = "The Less I Know The Better",
                    image = "https://i.scdn.co/image/ab67616d0000b273d0b0b5e2a5a0a0b0c0a0b0b0",
                    artist = "Tame Impala"
                )
            )
        )
    }
}