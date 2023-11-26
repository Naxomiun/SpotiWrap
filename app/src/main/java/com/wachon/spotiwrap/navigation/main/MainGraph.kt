package com.wachon.spotiwrap.navigation.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph.Home
import com.wachon.spotiwrap.core.navigation.MainGraph.Preview
import com.wachon.spotiwrap.core.navigation.MainGraph.Profile
import com.wachon.spotiwrap.core.navigation.MainGraph.Top
import com.wachon.spotiwrap.features.collage.presentation.PreviewScreen
import com.wachon.spotiwrap.features.home.presentation.HomeScreen
import com.wachon.spotiwrap.features.presentation.artist.ArtistScreen
import com.wachon.spotiwrap.features.presentation.track.TrackScreen
import com.wachon.spotiwrap.features.profile.presentation.profilescreen.ProfileScreen
import com.wachon.spotiwrap.features.top.presentation.TopScreen
import com.wachon.spotiwrap.ui.AppState

@Composable
fun MainGraph(
    appState: AppState,
    homeListState: LazyListState,
    topListState: LazyListState,
    artistDetailState: LazyListState,
    trackDetailState: LazyListState,
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
            route = "artistDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let {
                ArtistScreen(
                    listState = artistDetailState,
                    id = id
                )
            }
        }

        composable(
            route = "trackDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("id")?.let {
                TrackScreen(
                    listState = trackDetailState,
                    id = id
                )
            }
        }

        composable(
            route = Top.route
        ) {
            TopScreen(
                listState = topListState
            )
        }

        composable(
            route = Preview.route
        ) {
            PreviewScreen {
                appState.navigateUp()
            }
        }

        composable(
            route = Profile.route
        ) {
            ProfileScreen {
                appState.navigateToPreview()
            }
        }
    }

}