package com.wachon.spotiwrap.navigation.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph.*
import com.wachon.spotiwrap.features.menu.presentation.HomeScreen
import com.wachon.spotiwrap.ui.AppState

@Composable
fun MainGraph(
    appState: AppState,
    homeListState: LazyListState
) {

    NavHost(
        navController = appState.mainNavController,
        route = GRAPH.Main,
        startDestination = Home.route)
    {
        composable(Home.route) {
            HomeScreen(
                listState = homeListState
            )
        }

        composable(Profile.route) {
            Text("Profile")
        }

        composable(Top.route) {
            Text("Top")
        }
    }

}