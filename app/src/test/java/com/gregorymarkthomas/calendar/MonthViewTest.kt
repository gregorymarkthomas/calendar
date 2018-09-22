package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.month_view.view.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

/**
 * This is considered an INTEGRATION test suite - it utilises the Activity, therefore forcing the 'real' Model
 * TODO() - Crash in Model
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/Presenter anyway.
 * But the test below is an example that it works.
 *
 * Roboelectric allows us to mock the Android code for use in JUnit tests.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MonthViewTest {

    lateinit var view: MonthView

    @Before
    fun setUp() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)
        view = activity.getMostRecentView() as MonthView
    }

    @Test
    fun presenter_attached_to_view() {
        assertNotNull(view.view)
        assertNotNull(view.view!!.dateTextView)
        assertNotEquals("", view.view!!.dateTextView.text)
    }

    @Test
    fun thirty_day_views_show_for_september() {
        /** Create 30 AppDay objects, without Events **/
        val days = List(30) {
            AppDay(it + 1, 9, 2018)
        }

        assertEquals(30, days.size)
        assertEquals(1, days[0].dayOfMonth)
        assertEquals(2, days[1].dayOfMonth)
        assertEquals(3, days[2].dayOfMonth)

        view.showDates(days)

        assertEquals(30, view.view!!.calendarRecyclerView.adapter.itemCount)
    }
}