package com.wachon.spotiwrap.features.splash.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wachon.spotiwrap.core.design.components.collectEvents
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    collectEvents {
        viewModel.event.collectLatest {
            when (it) {
                is SplashViewModel.Event.NavigateToLogin -> navigateToLogin()
                is SplashViewModel.Event.NavigateToHome -> navigateToHome()
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.checkForPersistedLogin()
    }

    SplashContent(
        state = state
    )
}

@Composable
fun SplashContent(
    state: SplashViewModel.State,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "Splash Screen ole ole ole cargando")
    }
}