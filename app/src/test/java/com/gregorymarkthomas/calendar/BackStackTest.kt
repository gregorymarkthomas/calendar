package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.model.ModelInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStack
import com.gregorymarkthomas.calendar.util.backstack.BackStackCallback
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.interfaces.LayoutContextInterface
import com.gregorymarkthomas.calendar.view.LifeCycleView
import com.gregorymarkthomas.calendar.view.MonthView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*
import kotlin.test.assertEquals

//@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class)
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
    private lateinit var model: ModelInterface
    @Mock
    private lateinit var layout: LayoutContextInterface

    lateinit var view: LifeCycleView

    @Before
    fun setUp() {
//        var activity = Robolectric.setupActivity(MainActivity::class.java)
//        view = activity.getRecentViewClass()
    }

    @Test
    fun has_default_view() {
        val backstack = BackStack(callback, MonthView::class.java, Date(), backstack, model, layout)

        /** then **/
        assertEquals(MonthView::class.java, backstack.getRecentViewClass())
    }

    // TODO() - add more tests
}