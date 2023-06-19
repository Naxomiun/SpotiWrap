package com.wachon.spotiwrap.navigation.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph.Home
import com.wachon.spotiwrap.core.navigation.MainGraph.Profile
import com.wachon.spotiwrap.core.navigation.MainGraph.Top
import com.wachon.spotiwrap.features.home.presentation.HomeScreen
import com.wachon.spotiwrap.features.profile.presentation.profilescreen.ProfileScreen
import com.wachon.spotiwrap.ui.AppState

@Composable
fun MainGraph(
    appState: AppState,
    homeListState: LazyListState
) {

    NavHost(
        navController = appState.mainNavController,
        route = GRAPH.Main,
        startDestination = Home.route
    ) {
        composable(
            route = Home.route
        ) {
            HomeScreen(
                listState = homeListState
            )
        }

        composable(
            route = Top.route
        ) {
            Text("TOP")
        }

        composable(
            route = Profile.route
        ) {
            ProfileScreen()
        }

    }

}