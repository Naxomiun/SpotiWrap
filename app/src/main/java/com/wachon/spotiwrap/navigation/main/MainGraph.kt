package com.wachon.spotiwrap.navigation.main

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph.Album
import com.wachon.spotiwrap.core.navigation.MainGraph.Artist
import com.wachon.spotiwrap.core.navigation.MainGraph.Home
import com.wachon.spotiwrap.core.navigation.MainGraph.Preview
import com.wachon.spotiwrap.core.navigation.MainGraph.Profile
import com.wachon.spotiwrap.core.navigation.MainGraph.Top
import com.wachon.spotiwrap.core.navigation.MainGraph.Track
import com.wachon.spotiwrap.features.collage.presentation.PreviewScreen
import com.wachon.spotiwrap.features.home.presentation.HomeScreen
import com.wachon.spotiwrap.features.presentation.album.AlbumScreen
import com.wachon.spotiwrap.features.presentation.artist.ArtistScreen
import com.wachon.spotiwrap.features.presentation.track.TrackScreen
import com.wachon.spotiwrap.features.profile.presentation.profilescreen.ProfileScreen
import com.wachon.spotiwrap.features.top.presentation.TopScreen
import com.wachon.spotiwrap.ui.AppState

@Composable
fun MainGraph(
    appState: AppState,
    homeListState: LazyListState,
    topListState: LazyListState
) {

    NavHost(
        navController = appState.mainNavController,
        route = GRAPH.Main,
        startDestination = Home.route
    ) {

        //region Bottom Bar
        composable(
            route = Home.route
        ) {
            HomeScreen(
                hazeStateProvider = { appState.hazeState },
                listState = homeListState,
                onTrackSelected = { appState.navigateToTrackDetail(id = it) },
                onArtistSelected = { appState.navigateToArtistDetail(id = it) }
            )
        }

        composable(
            route = Profile.route
        ) {
            ProfileScreen(
                hazeStateProvider = { appState.hazeState },
            ) {
                appState.navigateToPreview()
            }
        }

        composable(
            route = Top.route
        ) {
            TopScreen(
                hazeStateProvider = { appState.hazeState },
                listState = topListState,
                onTrackSelected = {
                    appState.navigateToTrackDetail(it)
                },
                onArtistSelected = {
                    appState.navigateToArtistDetail(it)
                },
            )
        }

        //endregion Bottom Bar

        composable(
            route = Artist.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            ArtistScreen(
                listState = rememberLazyListState(),
                onBackPressed = { appState.navigateUp() },
                onTrackSelected = { appState.navigateToTrackDetail(id = it) },
                onAlbumSelected = { appState.navigateToAlbumDetail(id = it) },
                onArtistSelected = { appState.navigateToArtistDetail(id = it) }
            )
        }

        composable(
            route = Track.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            TrackScreen(
                listState = rememberLazyListState(),
                onBackPressed = { appState.navigateUp() },
                onAlbumSelected = { appState.navigateToAlbumDetail(id = it) },
                onArtistSelected = { appState.navigateToArtistDetail(id = it) }
            )
        }

        composable(
            route = Album.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(500)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(500)
                )
            }
        ) {
            AlbumScreen(
                listState = rememberLazyListState(),
                onBackPressed = { appState.navigateUp() },
                onTrackSelected = { appState.navigateToTrackDetail(id = it) },
                onArtistSelected = { appState.navigateToArtistDetail(id = it) })
        }



        composable(
            route = Preview.route
        ) {
            PreviewScreen {
                appState.navigateUp()
            }
        }

    }

}