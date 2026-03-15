package com.tasbeeh.counter.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tasbeeh.counter.data.preferences.ThemeMode
import com.tasbeeh.counter.data.preferences.UserPreferences
import com.tasbeeh.counter.ui.navigation.Screen
import com.tasbeeh.counter.ui.screens.counter.CounterScreen
import com.tasbeeh.counter.ui.screens.history.HistoryScreen
import com.tasbeeh.counter.ui.screens.settings.SettingsScreen
import com.tasbeeh.counter.ui.theme.TasbeehCounterTheme

@Composable
fun TasbeehApp() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val themeMode by userPreferences.themeMode.collectAsState(initial = ThemeMode.SYSTEM)

    TasbeehCounterTheme(themeMode = themeMode) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Counter.route
        ) {
            composable(Screen.Counter.route) {
                CounterScreen(
                    onNavigateToHistory = {
                        navController.navigate(Screen.History.route)
                    },
                    onNavigateToSettings = {
                        navController.navigate(Screen.Settings.route)
                    }
                )
            }

            composable(Screen.History.route) {
                HistoryScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Screen.Settings.route) {
                SettingsScreen(
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
