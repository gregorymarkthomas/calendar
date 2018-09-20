package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.month_view.view.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnit
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull

/**
 * TODO - Try to avoid Roboelectric. Seems a bit finicky.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MonthViewTest {

    lateinit var view: MonthView

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** Objects that class we're testing relies on require mocking **/
    /** TODO() - Do we need a '@Mock' here? **/
    private lateinit var presenter: CalendarPresenter

    @Before
    fun setUp() {
        var activity = Robolectric.setupActivity(MainActivity::class.java)
        view = activity.getRecentViewClass() as MonthView
    }

    @Test
    fun presenter_attached_to_view() {
        assertNotNull(view)
        assertNotNull(view.dateTextView)
        assertNotEquals("", view.dateTextView.text)
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

        assertEquals(30, view.calendarRecyclerView.adapter.itemCount)
    }
}