package com.gregorymarkthomas.calendar.tests

import android.support.test.runner.AndroidJUnit4
import com.gregorymarkthomas.calendar.rules.MainActivityTestRule
import com.gregorymarkthomas.calendar.view.MonthView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MonthViewTest {
    val TAG = "MonthViewTest"

    @get:Rule
    val rule = MainActivityTestRule(MonthView::class.java)

    // TODO()
    @Test
    fun calendar_shows_day() {
        /** **/
        rule.view.getLayout()
    }
}