package com.wachon.spotiwrap.ui

import androidx.compose.runtime.Composable
import com.wachon.spotiwrap.core.design.theme.SpotiWrapTheme

@Composable
fun SpotiWRAPP(appState: AppState = rememberAppState()) {
    SpotiWrapTheme {
        SpotiWrapNavHost(
            appState = appState
        )
    }
}