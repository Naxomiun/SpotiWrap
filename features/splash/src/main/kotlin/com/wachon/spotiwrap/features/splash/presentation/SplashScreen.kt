package com.wachon.spotiwrap.features.splash.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.wachon.spotiwrap.core.design.components.LoadingView
import com.wachon.spotiwrap.core.design.components.collectEvents
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = koinViewModel(),
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
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

    LoadingView()

}

