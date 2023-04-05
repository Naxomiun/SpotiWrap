package com.wachon.spotiwrap

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.presentation.login.Login
import com.wachon.spotiwrap.presentation.wrap.Wrap

@Composable
fun SpotiWrapNavHost(
    appState: MainActivityState = rememberMainActivityState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) { Login() }
        composable(Screen.Wrap.route) { Wrap() }
    }
}