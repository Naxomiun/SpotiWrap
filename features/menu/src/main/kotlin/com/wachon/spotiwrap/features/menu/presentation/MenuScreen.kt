package com.wachon.spotiwrap.features.menu.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun MenuScreen(
    navigateToWrap: () -> Unit,
    viewModel: MenuViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    MenuContent(
        state = state,
    )
}

@Composable
fun MenuContent(
    state: MenuViewModel.State,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItem(state = state)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(
    state: MenuViewModel.State
) {
    //Falla algunas veces, no sé si es por mi internet que da socket exception
    GlideImage(model = state.userProfile?.images?.first()?.url, contentDescription = "perfil")
    Text(text = state.userProfile?.displayName.toString())
}