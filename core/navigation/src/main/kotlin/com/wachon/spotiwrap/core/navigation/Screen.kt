sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Menu : Screen("menu")
    object Wrap : Screen("wrap")
}