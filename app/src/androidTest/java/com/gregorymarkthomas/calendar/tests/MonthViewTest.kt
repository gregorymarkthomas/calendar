package com.gregorymarkthomas.calendar.tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.rules.MainActivityTestRule
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.month_view.view.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.DateFormatSymbols
import java.util.*

/**
 * This is considered an INTEGRATION test suite - it utilises the Activity, therefore forcing the 'real' Model
 * TODO() - Crash in Model
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/Presenter anyway.
 * But the test below is an example that it works.
 */
@RunWith(AndroidJUnit4::class)
class MonthViewTest {
    val TAG = "MonthViewTest"

    @get:Rule
    val rule = MainActivityTestRule(MonthView::class.java)

    @Test
    fun calendar_shows_day() {
        /** Get today's date **/
        val calendar = CalendarHelper.getNewCalendar()
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val monthStr = CalendarHelper.getMonthString(month)
        val year = calendar.get(Calendar.YEAR)
        onView(withId(R.id.dateTextView)).check(matches(withText("$dayOfMonth $monthStr $year")))
    }
}