package com.tasbeeh.counter

import com.tasbeeh.counter.ui.navigation.Screen
import org.junit.Assert.assertEquals
import org.junit.Test

class NavigationTest {

    @Test
    fun `Screen routes are correct`() {
        assertEquals("counter", Screen.Counter.route)
        assertEquals("history", Screen.History.route)
        assertEquals("settings", Screen.Settings.route)
    }
}
