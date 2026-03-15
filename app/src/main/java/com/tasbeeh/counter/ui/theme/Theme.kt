package com.tasbeeh.counter.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.tasbeeh.counter.data.preferences.ThemeMode

private val LightColorScheme = lightColorScheme(
    primary = IslamicGreen,
    onPrimary = OnPrimary,
    primaryContainer = IslamicGreenLight,
    onPrimaryContainer = OnPrimary,
    secondary = GoldAmber,
    onSecondary = OnSecondary,
    secondaryContainer = GoldAmberLight,
    onSecondaryContainer = OnSecondary,
    background = BackgroundLight,
    onBackground = TextPrimaryLight,
    surface = SurfaceLight,
    onSurface = TextPrimaryLight,
    surfaceVariant = BackgroundLight,
    onSurfaceVariant = TextSecondaryLight,
    outline = DividerLight,
    error = ErrorRed,
    onError = OnPrimary
)

private val DarkColorScheme = darkColorScheme(
    primary = IslamicGreenLight,
    onPrimary = OnPrimary,
    primaryContainer = IslamicGreen,
    onPrimaryContainer = OnPrimary,
    secondary = GoldAmberLight,
    onSecondary = OnSecondary,
    secondaryContainer = GoldAmberDark,
    onSecondaryContainer = OnPrimary,
    background = BackgroundDark,
    onBackground = TextPrimaryDark,
    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = TextSecondaryDark,
    outline = DividerDark,
    error = ErrorRed,
    onError = OnPrimary
)

@Composable
fun TasbeehCounterTheme(
    themeMode: ThemeMode = ThemeMode.SYSTEM,
    content: @Composable () -> Unit
) {
    val darkTheme = when (themeMode) {
        ThemeMode.LIGHT -> false
        ThemeMode.DARK -> true
        ThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
