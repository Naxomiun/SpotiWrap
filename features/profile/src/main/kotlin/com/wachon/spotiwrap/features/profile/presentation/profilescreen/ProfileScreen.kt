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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.common.R
import com.wachon.spotiwrap.core.design.components.ButtonDefault
import com.wachon.spotiwrap.core.design.components.InfoBox
import com.wachon.spotiwrap.core.design.components.ProfileUserImage
import com.wachon.spotiwrap.core.design.components.TextNoPadding
import com.wachon.spotiwrap.core.design.extensions.customHaze
import com.wachon.spotiwrap.core.design.theme.Title
import dev.chrisbanes.haze.HazeState
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = koinViewModel(),
    hazeStateProvider: () -> HazeState,
    navigateToPreview: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedProfileContent(
        state = state,
        navigateToPreview = navigateToPreview,
        hazeStateProvider = hazeStateProvider
    )
}


@Composable
fun AnimatedProfileContent(
    state: ProfileScreenState,
    modifier: Modifier = Modifier,
    hazeStateProvider: () -> HazeState,
    navigateToPreview: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        ProfileContent(state, navigateToPreview, hazeStateProvider)
    }
}

@Composable
fun ProfileContent(
    state: ProfileScreenState,
    navigateToPreview: () -> Unit,
    hazeStateProvider: () -> HazeState,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .customHaze(
                hazeStateProvider = hazeStateProvider,
                backgroundColor = MaterialTheme.colorScheme.surface
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileUserImage(
            modifier = Modifier
                .padding(top = 80.dp),
            imageUrl = state.userProfile?.image ?: "",
            size = 100.dp
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextNoPadding(text = state.userProfile?.displayName ?: "", style = Title)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            InfoBox(
                data = state.userProfile?.followers ?: 0,
                dataName = LocalContext.current.getString(R.string.detail_followers),
                modifier = Modifier
                    .weight(1f)
            )

            InfoBox(
                data = state.userPlaylists.size,
                dataName = LocalContext.current.getString(R.string.profile_info_playlists),
                modifier = Modifier
                    .weight(1f)
            )

        }
        Spacer(modifier = Modifier.height(24.dp))
        ButtonDefault(
            text = LocalContext.current.getString(R.string.profile_check_your_top),
            icon = Icons.Filled.PlayArrow
        ) {
            navigateToPreview.invoke()
        }
    }
}
