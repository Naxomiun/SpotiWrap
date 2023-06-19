package com.wachon.spotiwrap.features.profile.presentation.profilescreen

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.model.UserProfileModel
import com.wachon.spotiwrap.core.design.components.BlurryBlobBackground
import com.wachon.spotiwrap.core.design.components.ProfileUserImage
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI
import com.wachon.spotiwrap.features.profile.presentation.profilebar.ProfileTopBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedProfileContent(
        state = state
    )
}


@Composable
fun AnimatedProfileContent(
    state: UserUI?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        ProfileContent(state)
    }
}

@Composable
fun ProfileContent(state: UserUI?) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileUserImage(
            modifier = Modifier
                .padding(top = 80.dp),
            imageUrl = state?.image ?: "",
            size = 100.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextNoPadding(text = state?.displayName ?: "", style = Title)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            BlurrySquareBox(
                modifier = Modifier
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                ) {
                    TextNoPadding(text = state?.followers.toString(), style = Title, color = BubblegumPink)
                    TextNoPadding(text = "followers", style = SmallTitle, color = MaterialTheme.colorScheme.onSurface)
                }
            }

            BlurrySquareBox(
                modifier = Modifier
                    .weight(1f)
            ) {
                TextNoPadding(text = "lalalal")
            }
        }

    }
}


@Composable
fun BlurrySquareBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15))
            .shadow(15.dp)
            .background(MaterialTheme.colorScheme.surface)
            .aspectRatio(1f)
    ) {
        content()
    }

}