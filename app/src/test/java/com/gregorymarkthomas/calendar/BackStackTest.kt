package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.presenter.contracts.AndroidPermissionContract
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.view.DayView
import com.gregorymarkthomas.calendar.view.EventBlockView
import com.gregorymarkthomas.calendar.view.EventView
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
 * TODO() - fix these tests.
 *
 * https://blog.kotlin-academy.com/mocking-is-not-rocket-science-expected-behavior-and-behavior-verification-3862dd0e0f03
 * every { mock.call(more(5)) } returns 1
 * every { mock.call(or(less(5), eq(5))) } returns -1
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BackStackTest {

    /**
     * 'relaxUnitFun = true' will stop 'io.mockk.MockKException: no answer found for...' error for our simple use of a mocked BackStackCallback
     */
    private val callback: BackStackCallback = mockk(relaxUnitFun = true)
    private val permissionsContract: AndroidPermissionContract = mockk(relaxUnitFun = true)

    @BeforeEach
    fun init() {
        clearMocks(callback)
    }

    @Test
    fun `has default view`() {
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** then **/
        assertNotNull(backstack.getMostRecentView())
        assertEquals(MonthView::class.java, backstack.getMostRecentView()::class.java)
    }

    @Test
    fun `views are added to the stack`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Add more views **/
        backstack.goTo(DayView(Date()))
        backstack.goTo(EventView())

        /** Check stack has the 3 views **/
        val stack = backstack.getCurrentViewClasses()
        assertEquals(3, stack.size)
        assertEquals("MonthView", stack[0])
        assertEquals("DayView", stack[1])
        assertEquals("EventView", stack[2])
    }


    /**
     * If the stack has x different views in the stack, and we want to show the FIRST view again (which is at the bottom of the stack),
     * then if we return the stack we should only see the chosen item.
     */
    @Test
    fun `re-uses first stack item and clears rest of stack`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Add more views **/
        backstack.goTo(DayView(Date()))
        backstack.goTo(EventView())

        /** Re-use FIRST view **/
        backstack.goTo(MonthView(Date(), permissionsContract))

        /** Check stack has only the 1 view **/
        val stack = backstack.getCurrentViewClasses()
        assertEquals(1, stack.size)
        assertEquals("MonthView", stack[0])
    }

    /**
     * If the stack has x different views in the stack, and we want to show the SECOND view again (which is at the bottom of the stack + 1),
     * then if we return the stack we should only see the chosen item and only one before it.
     */
    @Test
    fun `re-uses second stack item and clears rest of stack`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Add more views **/
        backstack.goTo(DayView(Date()))
        backstack.goTo(EventView())

        /** Re-use the SECOND view **/
        backstack.goTo(DayView(Date()))

        /** Check stack has only the 2 views **/
        val stack = backstack.getCurrentViewClasses()
        assertEquals(2, stack.size)
        assertEquals("MonthView", stack[0])
        assertEquals("DayView", stack[1])
    }

    /**
     * If the stack has x different views in the stack, and we want to show the LATEST view again (which is at the top of the stack),
     * then if we return the stack we should see the stack unchanged.
     */
    @Test
    fun `re-uses last stack item and maintains stack`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Add more views **/
        backstack.goTo(DayView(Date()))
        backstack.goTo(EventView())

        /** Re-use the LAST view **/
        backstack.goTo(EventView())

        /** Check stack still has 3 views **/
        val stack = backstack.getCurrentViewClasses()
        assertEquals(3, stack.size)
        assertEquals("MonthView", stack[0])
        assertEquals("DayView", stack[1])
        assertEquals("EventView", stack[2])
    }


    @Test
    fun `getMostRecentView() keeps working correctly after adding new views`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Check 'getMostRecentView()' works **/
        assertEquals(MonthView::class.java, backstack.getMostRecentView()::class.java)


        /** Add another view **/
        backstack.goTo(DayView(Date()))

        /** Check 'getMostRecentView()' works **/
        assertEquals(DayView::class.java, backstack.getMostRecentView()::class.java)

        /** Add another view **/
        backstack.goTo(EventView())

        /** Check 'getMostRecentView()' works **/
        assertEquals(EventView::class.java, backstack.getMostRecentView()::class.java)
    }

    @Test
    fun `using goBack() on a full stack removes items until one stack item left`() {
        /** Instantiate with default view **/
        val backstack = BackStack(callback, MonthView(Date(), permissionsContract))

        /** Add more views **/
        backstack.goTo(DayView(Date()))
        backstack.goTo(EventView())

        /** 'goBack()' should remove the latest item from stack **/
        backstack.goBack()
        var stack = backstack.getCurrentViewClasses()
        assertEquals(2, stack.size)
        assertEquals("MonthView", stack[0])
        assertEquals("DayView", stack[1])

        /** 'goBack()' should remove the latest item from stack **/
        backstack.goBack()
        stack = backstack.getCurrentViewClasses()
        assertEquals(1, stack.size)
        assertEquals("MonthView", stack[0])

        /** 'goBack()' should MAINTAIN the latest item from stack, as it's the only item left **/
        backstack.goBack()
        stack = backstack.getCurrentViewClasses()
        assertEquals(1, stack.size)
        assertEquals("MonthView", stack[0])
    }
}