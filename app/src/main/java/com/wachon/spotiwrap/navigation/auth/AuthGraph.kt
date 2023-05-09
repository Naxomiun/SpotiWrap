package com.wachon.spotiwrap.navigation.auth

import com.wachon.spotiwrap.core.navigation.AuthGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.features.login.presentation.LoginScreen
import com.wachon.spotiwrap.features.splash.presentation.SplashScreen
import com.wachon.spotiwrap.ui.AppState

fun NavGraphBuilder.authNavGraph(
    appState: AppState
) {

    navigation(
        route = GRAPH.Auth,
        startDestination = AuthGraph.Splash.route,
    ) {
        composable(AuthGraph.Splash.route) {
            SplashScreen(
                navigateToLogin = { appState.navigateToLogin() },
                navigateToHome = { appState.navigateToMainGraph() }
            )
        }
        composable(AuthGraph.Login.route) {
            LoginScreen(
                navigateToHome = { appState.navigateToMainGraph() }
            )
        }
    }
}

