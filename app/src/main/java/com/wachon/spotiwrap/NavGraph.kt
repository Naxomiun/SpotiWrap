package com.wachon.spotiwrap

import Screen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.features.login.presentation.LoginScreen
import com.wachon.spotiwrap.features.menu.presentation.MenuScreen
import com.wachon.spotiwrap.features.splash.presentation.SplashScreen

@Composable
fun SpotiWrapNavHost(
    appState: AppState = rememberAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToLogin = {
                    appState.navigateToLogin()
                },
                navigateToMenu = {
                    appState.navigateToMenu()
                }
            )
        }
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
    }
}