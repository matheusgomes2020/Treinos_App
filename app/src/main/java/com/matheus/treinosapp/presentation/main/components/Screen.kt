package com.matheus.treinosapp.presentation.main.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.matheus.treinosapp.R

sealed class Screen( val route: String, @StringRes val label: Int, @DrawableRes val icon: Int ) {
    object Home: Screen(HOME, R.string.home, R.drawable.home)
    object Add: Screen(INGREDIENTS, R.string.add, R.drawable.grid)
    object Favorites: Screen(FAVORITES, R.string.favorites, R.drawable.bookmarks)

    companion object {
        const val HOME = "home"
        const val INGREDIENTS = "add"
        const val FAVORITES = "favorites"
    }
}

val bottomNavScreens = listOf(
    Screen.Home,
    Screen.Add,
    Screen.Favorites
)