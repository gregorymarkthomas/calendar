package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface
import com.gregorymarkthomas.calendar.util.interfaces.GetSharedPreferencesInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.view.MonthView
import com.nhaarman.mockito_kotlin.then
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*
import kotlin.test.assertEquals


class BackStackTest {

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField
    @Rule
    var mockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var callback: BackStackCallback
    @Mock
    private lateinit var backstack: BackStackInterface
    @Mock
    private lateinit var resolver: ContentResolverInterface
    @Mock
    private lateinit var preferences: GetSharedPreferencesInterface
    @Mock
    private lateinit var context: LayoutContextInterface

    @Before
    fun setUp() {
        // Do nothing
    }

    @Test
    fun has_default_view() {
        val backstack = BackStack(callback, MonthView::class.java, Date(), backstack, resolver, preferences, context)

        /** then **/
        assertEquals(MonthView::class.java, backstack.getCurrentView()::class.java)
    }

    // TODO() - add more tests
}