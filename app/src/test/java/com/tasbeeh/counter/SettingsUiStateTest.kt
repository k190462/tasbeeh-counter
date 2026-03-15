package com.tasbeeh.counter

import com.tasbeeh.counter.data.preferences.FontSizeOption
import com.tasbeeh.counter.data.preferences.ThemeMode
import com.tasbeeh.counter.ui.screens.settings.SettingsUiState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SettingsUiStateTest {

    @Test
    fun `default SettingsUiState values`() {
        val state = SettingsUiState()
        assertTrue(state.soundEnabled)
        assertTrue(state.hapticEnabled)
        assertEquals(ThemeMode.SYSTEM, state.themeMode)
        assertEquals(FontSizeOption.MEDIUM, state.fontSize)
    }

    @Test
    fun `SettingsUiState with custom values`() {
        val state = SettingsUiState(
            soundEnabled = false,
            hapticEnabled = false,
            themeMode = ThemeMode.DARK,
            fontSize = FontSizeOption.LARGE
        )
        assertFalse(state.soundEnabled)
        assertFalse(state.hapticEnabled)
        assertEquals(ThemeMode.DARK, state.themeMode)
        assertEquals(FontSizeOption.LARGE, state.fontSize)
    }

    @Test
    fun `ThemeMode has all expected values`() {
        val modes = ThemeMode.entries
        assertEquals(3, modes.size)
        assertTrue(modes.contains(ThemeMode.LIGHT))
        assertTrue(modes.contains(ThemeMode.DARK))
        assertTrue(modes.contains(ThemeMode.SYSTEM))
    }

    @Test
    fun `FontSizeOption has all expected values`() {
        val sizes = FontSizeOption.entries
        assertEquals(3, sizes.size)
        assertTrue(sizes.contains(FontSizeOption.SMALL))
        assertTrue(sizes.contains(FontSizeOption.MEDIUM))
        assertTrue(sizes.contains(FontSizeOption.LARGE))
    }

    @Test
    fun `SettingsUiState copy updates correctly`() {
        val original = SettingsUiState()
        val updated = original.copy(soundEnabled = false, themeMode = ThemeMode.LIGHT)

        assertFalse(updated.soundEnabled)
        assertTrue(updated.hapticEnabled)
        assertEquals(ThemeMode.LIGHT, updated.themeMode)
        assertEquals(FontSizeOption.MEDIUM, updated.fontSize)
    }
}
