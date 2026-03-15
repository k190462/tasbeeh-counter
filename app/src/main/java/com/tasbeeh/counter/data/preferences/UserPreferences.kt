package com.tasbeeh.counter.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

enum class ThemeMode { LIGHT, DARK, SYSTEM }
enum class FontSizeOption { SMALL, MEDIUM, LARGE }

class UserPreferences(private val context: Context) {

    companion object {
        private val SOUND_ENABLED = booleanPreferencesKey("sound_enabled")
        private val HAPTIC_ENABLED = booleanPreferencesKey("haptic_enabled")
        private val THEME_MODE = stringPreferencesKey("theme_mode")
        private val FONT_SIZE = stringPreferencesKey("font_size")
        private val COUNTER_VALUE = intPreferencesKey("counter_value")
    }

    val soundEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[SOUND_ENABLED] ?: true
    }

    val hapticEnabled: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[HAPTIC_ENABLED] ?: true
    }

    val themeMode: Flow<ThemeMode> = context.dataStore.data.map { prefs ->
        try {
            ThemeMode.valueOf(prefs[THEME_MODE] ?: ThemeMode.SYSTEM.name)
        } catch (_: IllegalArgumentException) {
            ThemeMode.SYSTEM
        }
    }

    val fontSize: Flow<FontSizeOption> = context.dataStore.data.map { prefs ->
        try {
            FontSizeOption.valueOf(prefs[FONT_SIZE] ?: FontSizeOption.MEDIUM.name)
        } catch (_: IllegalArgumentException) {
            FontSizeOption.MEDIUM
        }
    }

    val counterValue: Flow<Int> = context.dataStore.data.map { prefs ->
        prefs[COUNTER_VALUE] ?: 0
    }

    suspend fun setSoundEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[SOUND_ENABLED] = enabled
        }
    }

    suspend fun setHapticEnabled(enabled: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[HAPTIC_ENABLED] = enabled
        }
    }

    suspend fun setThemeMode(mode: ThemeMode) {
        context.dataStore.edit { prefs ->
            prefs[THEME_MODE] = mode.name
        }
    }

    suspend fun setFontSize(size: FontSizeOption) {
        context.dataStore.edit { prefs ->
            prefs[FONT_SIZE] = size.name
        }
    }

    suspend fun setCounterValue(value: Int) {
        context.dataStore.edit { prefs ->
            prefs[COUNTER_VALUE] = value
        }
    }
}
