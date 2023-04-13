package com.wachon.spotiwrap

import Screen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.features.login.presentation.LoginScreen
import com.wachon.spotiwrap.features.menu.presentation.MenuScreen

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
                navigateToMenu = {
                    appState.navigateToMenu()
                }
            )
        }
        composable(Screen.Menu.route) {
            MenuScreen()
        }
        composable(Screen.Wrap.route) { }
    }
}