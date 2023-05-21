package com.wachon.spotiwrap.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wachon.spotiwrap.core.design.components.BottomNavBarItem
import com.wachon.spotiwrap.core.navigation.AuthGraph
import com.wachon.spotiwrap.core.navigation.GRAPH
import com.wachon.spotiwrap.core.navigation.MainGraph
import com.wachon.spotiwrap.core.navigation.extensions.navigateAndPop
import com.wachon.spotiwrap.core.navigation.extensions.navigatePoppingUpToStartDestination

@Composable
fun rememberAppState(
    authNavController: NavHostController = rememberNavController(),
    mainNavController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(authNavController, mainNavController, context) {
    AppState(authNavController, mainNavController, context)
}

@Stable
class AppState(
    val authNavController: NavHostController,
    val mainNavController: NavHostController,
    private val context: Context
) {
    
    val currentRoute: String
        @Composable get() = mainNavController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun navigateToLogin() {
        authNavController.navigate(AuthGraph.Login.route)
    }

    fun navigateToMainGraph() {
        authNavController.popBackStack()
        authNavController.navigateAndPop(GRAPH.Main)
    }

    fun bottomNavigationTo(bottomNavBarItem: BottomNavBarItem) {
        when (bottomNavBarItem) {
            BottomNavBarItem.Home -> navigateToHome()
            BottomNavBarItem.Top -> navigateToTop()
            BottomNavBarItem.Profile -> navigateToProfile()
        }
    }

    private fun navigateToHome() {
        mainNavController.navigatePoppingUpToStartDestination(MainGraph.Home.route)
    }

    private fun navigateToTop() {
        mainNavController.navigatePoppingUpToStartDestination(MainGraph.Top.route)
    }

    private fun navigateToProfile() {
        mainNavController.navigatePoppingUpToStartDestination(MainGraph.Profile.route)
    }

}