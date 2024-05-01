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

object WorkoutsDetailsGraph {
    const val ROOT = "workouts_details_graph"
    const val DETAILS = "details"
}

object AddExercisesGraph {
    const val ROOT = "add_exercises_graph"
    const val ADD = "add"
}

object ExercisesDetailsGraph {
    const val ROOT = "exercises_details_graph"
    const val DETAILS = "exercise_details"
}
object AppGraph {
    val initial = RootGraph
    val auth = AuthGraph
    val workouts_details = WorkoutsDetailsGraph
    val exercises_details = ExercisesDetailsGraph
    val add_exercises = AddExercisesGraph
    val home = HomeGraph
    val splash = SplashGraph

}