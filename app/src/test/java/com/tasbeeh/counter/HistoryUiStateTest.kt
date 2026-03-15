package com.tasbeeh.counter

import com.tasbeeh.counter.ui.screens.history.HistoryUiState
import com.tasbeeh.counter.ui.screens.history.SortOption
import com.tasbeeh.counter.domain.model.SavedCount
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HistoryUiStateTest {

    @Test
    fun `default HistoryUiState has empty list`() {
        val state = HistoryUiState()
        assertTrue(state.savedCounts.isEmpty())
        assertEquals(SortOption.DATE_NEWEST, state.sortOption)
    }

    @Test
    fun `HistoryUiState with saved counts`() {
        val counts = listOf(
            SavedCount(id = 1, count = 100, timestamp = 1000L),
            SavedCount(id = 2, count = 200, timestamp = 2000L)
        )
        val state = HistoryUiState(savedCounts = counts)
        assertEquals(2, state.savedCounts.size)
        assertEquals(100, state.savedCounts[0].count)
        assertEquals(200, state.savedCounts[1].count)
    }

    @Test
    fun `HistoryUiState sort option change`() {
        val state = HistoryUiState(sortOption = SortOption.COUNT_HIGHEST)
        assertEquals(SortOption.COUNT_HIGHEST, state.sortOption)
    }

    @Test
    fun `SortOption has all expected values`() {
        val options = SortOption.entries
        assertEquals(4, options.size)
        assertTrue(options.contains(SortOption.DATE_NEWEST))
        assertTrue(options.contains(SortOption.DATE_OLDEST))
        assertTrue(options.contains(SortOption.COUNT_HIGHEST))
        assertTrue(options.contains(SortOption.COUNT_LOWEST))
    }
}
