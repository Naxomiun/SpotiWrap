package com.wachon.spotiwrap.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotiWRAPP(appState: AppState = rememberAppState()) {
    SpotiWrapTheme {
        Scaffold {
            SpotiWrapNavHost(
                appState = appState,
                paddingValues = it
            )
        }
    }
}