package com.wachon.spotiwrap.ui

import Screen
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wachon.spotiwrap.core.navigation.extensions.navigateAndPop
import com.wachon.spotiwrap.core.navigation.extensions.navigatePoppingUpToStartDestination

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    AppState(navController, context)
}

class AppState(val navController: NavHostController, private val context: Context) {
    
    val currentRoute: String
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun navigatePoppingUpToStartDestination(currentRoute: String, screen: Screen) {
        if (currentRoute != screen.route) {
            navController.navigatePoppingUpToStartDestination(screen.route)
        }
    }

    fun navigateAndPop(screen: Screen) {
        navController.navigateAndPop(screen.route)
    }

}