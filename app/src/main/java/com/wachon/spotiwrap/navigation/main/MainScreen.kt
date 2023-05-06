package com.wachon.spotiwrap.navigation.main

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.design.components.BottomNavBar
import com.wachon.spotiwrap.ui.AppState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    appState: AppState
) {

    val currentRoute = appState.currentRoute

    Scaffold(
        bottomBar = {
            BottomNavBar(
                currentRoute = { currentRoute },
                onSelectedItem = {
                    if (currentRoute != it.getScreenRoute()) {
                        appState.bottomNavigationTo(it)
                    }
                }
            )
        }
    ) { _ ->
        MainGraph(appState = appState)
    }
}

