package com.wachon.spotiwrap.navigation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.design.components.BottomNavBar
import com.wachon.spotiwrap.core.design.components.isScrollingUp
import com.wachon.spotiwrap.core.navigation.MainGraph.*
import com.wachon.spotiwrap.ui.AppState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    appState: AppState
) {
    val currentRoute = appState.currentRoute
    val homeListState = rememberLazyListState()
    val topListState = rememberLazyListState()
    val isHomeScrolling = if (currentRoute == Home.route) {
        homeListState.isScrollingUp()
    } else {
        true
    }
    val isTopListScrolling = if (currentRoute == Top.route) {
        topListState.isScrollingUp()
    } else {
        true
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                shouldShow = {
                    (isHomeScrolling && isTopListScrolling)
                            && (currentRoute == Preview.route).not()
                            && (currentRoute == Artist.route).not()
                            && (currentRoute == Track.route).not()
                            && (currentRoute == Album.route).not()
                },
                currentRoute = { currentRoute },
                onSelectedItem = {
                    if (currentRoute != it.getScreenRoute()) {
                        appState.bottomNavigationTo(it)
                    }
                },
                hazeStateProvider = { appState.hazeState }
            )
        }
    ) { _ ->
        MainGraph(
            appState = appState,
            homeListState = homeListState,
            topListState = topListState
        )
    }
}

