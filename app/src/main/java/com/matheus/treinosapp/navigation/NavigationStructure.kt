package com.matheus.treinosapp.navigation

object AuthGraph {
    const val ROOT = "auth_graph"
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val FORGOT_PASSWORD = "forgot_password"
}

object HomeGraph {
    const val ROOT = "home_graph"
    const val HOME = "home"
    const val ADD = "add"
    const val FAVORITES = "favorites"
}

object RootGraph {
    const val ROOT = "root_graph"
}

object SplashGraph {
    const val ROOT = "splash_graph"
    const val SPLASH = "splash"
}

object AppGraph {
    val initial = RootGraph
    val auth = AuthGraph
    val home = HomeGraph
    val splash = SplashGraph

}