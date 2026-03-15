package com.tasbeeh.counter.ui.screens.counter

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tasbeeh.counter.data.database.TasbeehDatabase
import com.tasbeeh.counter.data.preferences.UserPreferences
import com.tasbeeh.counter.data.repository.TasbeehRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class CounterUiState(
    val count: Int = 0,
    val showResetDialog: Boolean = false
)

sealed class CounterEvent {
    data object CountSaved : CounterEvent()
    data object CountReset : CounterEvent()
    data class MilestoneReached(val milestone: Int) : CounterEvent()
}

class CounterViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TasbeehRepository

    private val _uiState = MutableStateFlow(CounterUiState())
    val uiState: StateFlow<CounterUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<CounterEvent>()
    val events: SharedFlow<CounterEvent> = _events.asSharedFlow()

    val soundEnabled: StateFlow<Boolean>
    val hapticEnabled: StateFlow<Boolean>

    private val milestones = setOf(25, 50, 100, 500, 1000)

    init {
        val database = TasbeehDatabase.getDatabase(application)
        val userPreferences = UserPreferences(application)
        repository = TasbeehRepository(database.savedCountDao(), userPreferences)

        soundEnabled = repository.soundEnabled.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), true
        )
        hapticEnabled = repository.hapticEnabled.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), true
        )

        // Restore saved counter value
        viewModelScope.launch {
            repository.counterValue.collect { value ->
                _uiState.value = _uiState.value.copy(count = value)
            }
        }
    }

    fun increment() {
        val newCount = _uiState.value.count + 1
        _uiState.value = _uiState.value.copy(count = newCount)

        viewModelScope.launch {
            repository.saveCounterValue(newCount)

            if (newCount in milestones) {
                _events.emit(CounterEvent.MilestoneReached(newCount))
            }
        }
    }

    fun showResetDialog() {
        _uiState.value = _uiState.value.copy(showResetDialog = true)
    }

    fun dismissResetDialog() {
        _uiState.value = _uiState.value.copy(showResetDialog = false)
    }

    fun confirmReset() {
        _uiState.value = CounterUiState(count = 0, showResetDialog = false)
        viewModelScope.launch {
            repository.saveCounterValue(0)
            _events.emit(CounterEvent.CountReset)
        }
    }

    fun saveCount() {
        val currentCount = _uiState.value.count
        if (currentCount > 0) {
            viewModelScope.launch {
                repository.saveCount(currentCount)
                _events.emit(CounterEvent.CountSaved)
            }
        }
    }
}
