package com.wachon.spotiwrap.ui

import Screen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.features.login.presentation.LoginScreen
import com.wachon.spotiwrap.features.menu.presentation.HomeScreen
import com.wachon.spotiwrap.features.splash.presentation.SplashScreen

@Composable
fun SpotiWrapNavHost(
    appState: AppState,
    paddingValues: PaddingValues
) {

    NavHost(
        navController = appState.navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToLogin = { appState.navigateAndPop(Screen.Login) },
                navigateToHome = { appState.navigateAndPop(Screen.Home) }
            )
        }
        composable(Screen.Login.route) {
            LoginScreen(
                navigateToHome = { appState.navigateAndPop(Screen.Home) }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(paddingValues = paddingValues)
        }
    }

}