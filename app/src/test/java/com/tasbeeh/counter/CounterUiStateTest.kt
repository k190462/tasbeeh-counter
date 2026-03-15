package com.tasbeeh.counter

import com.tasbeeh.counter.ui.screens.counter.CounterUiState
import com.tasbeeh.counter.ui.screens.counter.CounterEvent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CounterUiStateTest {

    @Test
    fun `default CounterUiState has zero count`() {
        val state = CounterUiState()
        assertEquals(0, state.count)
        assertFalse(state.showResetDialog)
    }

    @Test
    fun `CounterUiState with custom count`() {
        val state = CounterUiState(count = 42)
        assertEquals(42, state.count)
    }

    @Test
    fun `CounterUiState showResetDialog`() {
        val state = CounterUiState(showResetDialog = true)
        assertTrue(state.showResetDialog)
    }

    @Test
    fun `CounterUiState copy updates correctly`() {
        val original = CounterUiState(count = 10, showResetDialog = false)
        val updated = original.copy(count = 11)

        assertEquals(11, updated.count)
        assertFalse(updated.showResetDialog)
    }

    @Test
    fun `CounterEvent MilestoneReached carries milestone value`() {
        val event = CounterEvent.MilestoneReached(100)
        assertEquals(100, event.milestone)
    }
}
