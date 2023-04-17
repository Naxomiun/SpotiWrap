package com.wachon.spotiwrap.features.splash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToMenu: () -> Unit,
    viewModel: SplashViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    SplashContent(
        state = state,
        navigateToLogin = { navigateToLogin.invoke() },
        navigateToMenu = { navigateToMenu.invoke() }
    )
}

@Composable
fun SplashContent(
    state: SplashViewModel.State,
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToMenu: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        state.scopesChecked?.let { if (it) navigateToMenu() else navigateToLogin() }
        Text(text = "Splash Screen ole ole ole cargando")
    }
}