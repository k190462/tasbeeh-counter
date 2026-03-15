package com.tasbeeh.counter.ui.navigation

sealed class Screen(val route: String) {
    data object Counter : Screen("counter")
    data object History : Screen("history")
    data object Settings : Screen("settings")
}
