package com.wachon.spotiwrap.features.profile.presentation.profilescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.ButtonDefault
import com.wachon.spotiwrap.core.design.components.ProfileUserImage
import com.wachon.spotiwrap.core.design.components.SquareBox
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.theme.BubblegumPink
import com.wachon.spotiwrap.core.design.theme.SmallTitle
import com.wachon.spotiwrap.core.design.theme.Title
import com.wachon.spotiwrap.features.profile.presentation.model.UserUI
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    navigateToPreview: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedProfileContent(
        state = state,
        navigateToPreview = navigateToPreview
    )
}


@Composable
fun AnimatedProfileContent(
    state: UserUI?,
    modifier: Modifier = Modifier,
    navigateToPreview: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        ProfileContent(state, navigateToPreview)
    }
}

@Composable
fun ProfileContent(
    state: UserUI?,
    navigateToPreview: () -> Unit
) {
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

            ProfileFollowersBox(
                followers = state?.followers ?: 0,
                modifier = Modifier
                    .weight(1f)
            )

            SquareBox(
                modifier = Modifier
                    .weight(1f)
            ) {

            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        ButtonDefault(
            text = "Check your Top",
            icon = Icons.Filled.PlayArrow
        ) {
            navigateToPreview.invoke()
        }
    }

}


@Composable
fun ProfileFollowersBox(
    followers: Int,
    modifier: Modifier = Modifier,
) {
    SquareBox(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.Start,
        ) {

            TextNoPadding(
                text = followers.toString(),
                style = Title.copy(fontSize = 40.sp),
                color = BubblegumPink
            )

            TextNoPadding(
                text = "followers",
                style = SmallTitle.copy(fontSize = 24.sp),
                color = MaterialTheme.colorScheme.onSurface
            )

        }
    }

}



