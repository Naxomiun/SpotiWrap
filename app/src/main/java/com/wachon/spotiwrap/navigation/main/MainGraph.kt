package com.wachon.spotiwrap.navigation.main

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph.Home
import com.wachon.spotiwrap.core.navigation.MainGraph.Profile
import com.wachon.spotiwrap.core.navigation.MainGraph.Top
import com.wachon.spotiwrap.features.collage.presentation.PreviewScreen
import com.wachon.spotiwrap.features.home.presentation.HomeScreen
import com.wachon.spotiwrap.features.recommender.presentation.RecommenderScreen
import com.wachon.spotiwrap.ui.AppState

@Composable
fun MainGraph(
    appState: AppState,
    homeListState: LazyListState,
    recommenderListState: LazyListState,
    collageListState: LazyListState,
) {

    NavHost(
        navController = appState.mainNavController,
        route = GRAPH.Main,
        startDestination = Home.route
    ) {
        composable(Home.route) {
            HomeScreen(
                listState = homeListState
            )
        }

        composable(Top.route) {
            RecommenderScreen(
                listState = recommenderListState
            )
        }

        composable(Profile.route) {
            PreviewScreen(
                listState = collageListState
            )
        }
    }

}