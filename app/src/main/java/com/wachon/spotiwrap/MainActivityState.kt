package com.wachon.spotiwrap

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Wrap : Screen("wrap")
}

@Composable
fun rememberMainActivityState(
    navController: NavHostController = rememberNavController(), context: Context = LocalContext.current
) = remember(navController, context) {
    MainActivityState(navController, context)
}

class MainActivityState(val navController: NavHostController, private val context: Context) {

    fun navigateToWrap() {
        navController.navigate(Screen.Wrap.route)
    }

}