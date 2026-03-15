package com.tasbeeh.counter.ui.screens.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tasbeeh.counter.data.database.TasbeehDatabase
import com.tasbeeh.counter.data.preferences.FontSizeOption
import com.tasbeeh.counter.data.preferences.ThemeMode
import com.tasbeeh.counter.data.preferences.UserPreferences
import com.tasbeeh.counter.data.repository.TasbeehRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class SettingsUiState(
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true,
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val fontSize: FontSizeOption = FontSizeOption.MEDIUM
)

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TasbeehRepository

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        val database = TasbeehDatabase.getDatabase(application)
        val userPreferences = UserPreferences(application)
        repository = TasbeehRepository(database.savedCountDao(), userPreferences)

        viewModelScope.launch {
            launch {
                repository.soundEnabled.collect { enabled ->
                    _uiState.value = _uiState.value.copy(soundEnabled = enabled)
                }
            }
            launch {
                repository.hapticEnabled.collect { enabled ->
                    _uiState.value = _uiState.value.copy(hapticEnabled = enabled)
                }
            }
            launch {
                repository.themeMode.collect { mode ->
                    _uiState.value = _uiState.value.copy(themeMode = mode)
                }
            }
            launch {
                repository.fontSize.collect { size ->
                    _uiState.value = _uiState.value.copy(fontSize = size)
                }
            }
        }
    }

    fun toggleSound() {
        viewModelScope.launch {
            repository.setSoundEnabled(!_uiState.value.soundEnabled)
        }
    }

    fun toggleHaptic() {
        viewModelScope.launch {
            repository.setHapticEnabled(!_uiState.value.hapticEnabled)
        }
    }

    fun setThemeMode(mode: ThemeMode) {
        viewModelScope.launch {
            repository.setThemeMode(mode)
        }
    }

    fun setFontSize(size: FontSizeOption) {
        viewModelScope.launch {
            repository.setFontSize(size)
        }
    }
}
