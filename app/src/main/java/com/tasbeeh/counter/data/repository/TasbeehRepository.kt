package com.tasbeeh.counter.data.repository

import com.tasbeeh.counter.data.database.SavedCountDao
import com.tasbeeh.counter.data.preferences.FontSizeOption
import com.tasbeeh.counter.data.preferences.ThemeMode
import com.tasbeeh.counter.data.preferences.UserPreferences
import com.tasbeeh.counter.domain.model.SavedCount
import kotlinx.coroutines.flow.Flow

class TasbeehRepository(
    private val savedCountDao: SavedCountDao,
    private val userPreferences: UserPreferences
) {
    // Counter persistence
    val counterValue: Flow<Int> = userPreferences.counterValue

    suspend fun saveCounterValue(value: Int) {
        userPreferences.setCounterValue(value)
    }

    // Saved counts
    fun getSavedCountsByDateDesc(): Flow<List<SavedCount>> = savedCountDao.getAllByDateDesc()
    fun getSavedCountsByDateAsc(): Flow<List<SavedCount>> = savedCountDao.getAllByDateAsc()
    fun getSavedCountsByCountDesc(): Flow<List<SavedCount>> = savedCountDao.getAllByCountDesc()
    fun getSavedCountsByCountAsc(): Flow<List<SavedCount>> = savedCountDao.getAllByCountAsc()

    suspend fun saveCount(count: Int) {
        savedCountDao.insert(SavedCount(count = count))
    }

    suspend fun deleteSavedCount(savedCount: SavedCount) {
        savedCountDao.delete(savedCount)
    }

    suspend fun deleteAllSavedCounts() {
        savedCountDao.deleteAll()
    }

    // Preferences
    val soundEnabled: Flow<Boolean> = userPreferences.soundEnabled
    val hapticEnabled: Flow<Boolean> = userPreferences.hapticEnabled
    val themeMode: Flow<ThemeMode> = userPreferences.themeMode
    val fontSize: Flow<FontSizeOption> = userPreferences.fontSize

    suspend fun setSoundEnabled(enabled: Boolean) = userPreferences.setSoundEnabled(enabled)
    suspend fun setHapticEnabled(enabled: Boolean) = userPreferences.setHapticEnabled(enabled)
    suspend fun setThemeMode(mode: ThemeMode) = userPreferences.setThemeMode(mode)
    suspend fun setFontSize(size: FontSizeOption) = userPreferences.setFontSize(size)
}
