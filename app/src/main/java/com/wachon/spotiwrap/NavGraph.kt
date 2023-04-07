package com.wachon.spotiwrap

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.features.login.presentation.LoginScreen

@Composable
fun SpotiWrapNavHost(
    appState: AppState = rememberAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                navigateToWrap = {
                    appState.navigateToWrap()
                }
            )
        }
        composable(Screen.Wrap.route) {  }
    }
}