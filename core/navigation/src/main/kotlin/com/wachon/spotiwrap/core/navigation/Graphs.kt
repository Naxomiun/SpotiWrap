package com.wachon.spotiwrap.core.navigation

object GRAPH {
    val Root = "root_graph"
    val Auth = "auth_graph"
    val Main = "main_graph"
}

enum class AuthGraph(val route: String) {
    Splash("splash"),
    Login("login")
}

enum class MainGraph(val route: String) {
    Home("home"),
    Preview("preview"),
    Top("top"),
    Profile("profile"),
    Artist("artist/{id}"),
    Track("track/{id}"),
    Album("album/{id}"),
}