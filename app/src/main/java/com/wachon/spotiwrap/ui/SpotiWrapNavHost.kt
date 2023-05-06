package com.wachon.spotiwrap.ui

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.navigation.auth.authNavGraph
import com.wachon.spotiwrap.navigation.main.MainScreen

@Composable
fun SpotiWrapNavHost(
    appState: AppState
) {

    NavHost(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        navController = appState.authNavController,
        route = GRAPH.Root,
        startDestination = GRAPH.Auth,
    ) {
        authNavGraph(
            appState = appState
        )
        composable(GRAPH.Main) {
            MainScreen(
                appState = appState
            )
        }
    }

}