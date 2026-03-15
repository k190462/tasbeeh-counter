package com.tasbeeh.counter

import com.tasbeeh.counter.domain.model.SavedCount
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SavedCountTest {

    @Test
    fun `create SavedCount with default values`() {
        val savedCount = SavedCount(count = 100)
        assertEquals(0L, savedCount.id)
        assertEquals(100, savedCount.count)
        assertTrue(savedCount.timestamp > 0)
    }

    @Test
    fun `create SavedCount with custom values`() {
        val timestamp = 1234567890L
        val savedCount = SavedCount(id = 1, count = 50, timestamp = timestamp)
        assertEquals(1L, savedCount.id)
        assertEquals(50, savedCount.count)
        assertEquals(timestamp, savedCount.timestamp)
    }

    @Test
    fun `SavedCount equality`() {
        val count1 = SavedCount(id = 1, count = 100, timestamp = 1000L)
        val count2 = SavedCount(id = 1, count = 100, timestamp = 1000L)
        val count3 = SavedCount(id = 2, count = 100, timestamp = 1000L)

        assertEquals(count1, count2)
        assertNotEquals(count1, count3)
    }

    @Test
    fun `SavedCount copy`() {
        val original = SavedCount(id = 1, count = 100, timestamp = 1000L)
        val copy = original.copy(count = 200)

        assertEquals(1L, copy.id)
        assertEquals(200, copy.count)
        assertEquals(1000L, copy.timestamp)
    }
}
