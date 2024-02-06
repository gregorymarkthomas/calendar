package com.gregorymarkthomas.calendar

import android.os.Build
import com.gregorymarkthomas.calendar.databinding.MonthViewBinding
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.view.MonthView
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
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/PresenterInterface anyway.
 * But the test below is an example that it works.
 *
 * Roboelectric allows us to mock the Android code for use in JUnit tests.
 */
@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.UPSIDE_DOWN_CAKE, minSdk = Build.VERSION_CODES.P) // Value of Build.VERSION_CODES.P is 28
class MonthViewTest {
    private lateinit var view: MonthView
    private lateinit var binding: MonthViewBinding

    @Before
    fun setUp() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        view = activity.getMostRecentView() as MonthView
        binding = MonthViewBinding.bind(view.view!!.rootView)
    }

    @Test
    fun presenter_attached_to_view() {
        assertNotNull(binding)
        assertNotNull(binding.dateTextView)
        assertNotEquals("", binding.dateTextView.text)
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

        assertEquals(30, binding.calendarRecyclerView.adapter!!.itemCount)
    }
}