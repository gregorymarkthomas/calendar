package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.view.MonthView
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestInstance
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

/**
 * Look here: https://blog.philipphauer.de/best-practices-unit-testing-kotlin/
 *
 * https://blog.kotlin-academy.com/mocking-is-not-rocket-science-expected-behavior-and-behavior-verification-3862dd0e0f03
 * every { mock.call(more(5)) } returns 1
 * every { mock.call(or(less(5), eq(5))) } returns -1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BackStackTest {

    val callback: BackStackCallback = mockk()

    @BeforeEach
    fun init() {
        clearMocks(callback)
    }

    @Test
    fun `has default view`() {
        val backstack = BackStack(callback, MonthView(Date()))

        /** then **/
        assertNotNull(backstack.getMostRecentView())
        assertEquals(MonthView::class.java, backstack.getMostRecentView()::class.java)
    }

    @Test
    fun `re-uses most recent item in stack`() {
        TODO()
    }

    // TODO() - add more tests
}