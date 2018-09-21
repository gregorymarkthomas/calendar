package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.view.MonthView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class BackStackTest {

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var callback: BackStackCallback

    @Before
    fun setUp() {}

    @Test
    fun has_default_view() {
        val backstack = BackStack(callback, MonthView(Date()))

        /** then **/
        assertNotNull(backstack.getMostRecentView())
        assertEquals(MonthView::class.java, backstack.getMostRecentView()::class.java)
    }

    // TODO() - add more tests
}