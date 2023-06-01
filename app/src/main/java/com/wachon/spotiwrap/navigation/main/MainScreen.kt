package com.wachon.spotiwrap.navigation.main

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.design.components.BottomNavBar
import com.wachon.spotiwrap.core.design.components.isScrollingUp
import com.wachon.spotiwrap.ui.AppState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appState: AppState
) {

    val currentRoute = appState.currentRoute

    val homeListState = rememberLazyListState()
    val recommenderListState = rememberLazyListState()

    val shouldShowBottomBar = homeListState.isScrollingUp()
    Scaffold(
        bottomBar = {
            BottomNavBar(
                shouldShow = { shouldShowBottomBar },
                currentRoute = { currentRoute },
                onSelectedItem = {
                    if (currentRoute != it.getScreenRoute()) {
                        appState.bottomNavigationTo(it)
                    }
                }
            )
        }
    ) { _ ->
        MainGraph(
            appState = appState,
            homeListState = homeListState,
            recommenderListState = recommenderListState
        )
    }
}

