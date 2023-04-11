sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Menu : Screen("menu")
    object Wrap : Screen("wrap")
}