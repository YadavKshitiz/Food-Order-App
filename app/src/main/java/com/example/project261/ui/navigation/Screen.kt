package com.example.project261.ui.navigation

sealed class Screen(
    val route: String
) {
    data object Home: Screen(route="home")

}