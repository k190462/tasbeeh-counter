package com.tasbeeh.counter.ui.screens.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tasbeeh.counter.data.database.TasbeehDatabase
import com.tasbeeh.counter.data.preferences.UserPreferences
import com.tasbeeh.counter.data.repository.TasbeehRepository
import com.tasbeeh.counter.domain.model.SavedCount
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class SortOption {
    DATE_NEWEST, DATE_OLDEST, COUNT_HIGHEST, COUNT_LOWEST
}

data class HistoryUiState(
    val savedCounts: List<SavedCount> = emptyList(),
    val sortOption: SortOption = SortOption.DATE_NEWEST
)

class HistoryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TasbeehRepository

    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        val database = TasbeehDatabase.getDatabase(application)
        val userPreferences = UserPreferences(application)
        repository = TasbeehRepository(database.savedCountDao(), userPreferences)

        loadSavedCounts()
    }

    private fun loadSavedCounts() {
        viewModelScope.launch {
            val flow = when (_uiState.value.sortOption) {
                SortOption.DATE_NEWEST -> repository.getSavedCountsByDateDesc()
                SortOption.DATE_OLDEST -> repository.getSavedCountsByDateAsc()
                SortOption.COUNT_HIGHEST -> repository.getSavedCountsByCountDesc()
                SortOption.COUNT_LOWEST -> repository.getSavedCountsByCountAsc()
            }
            flow.collect { counts ->
                _uiState.value = _uiState.value.copy(savedCounts = counts)
            }
        }
    }

    fun setSortOption(option: SortOption) {
        _uiState.value = _uiState.value.copy(sortOption = option)
        loadSavedCounts()
    }

    fun deleteCount(savedCount: SavedCount) {
        viewModelScope.launch {
            repository.deleteSavedCount(savedCount)
        }
    }
}
