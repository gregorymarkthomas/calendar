package com.gregorymarkthomas.calendar.tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withTagValue
import android.support.test.runner.AndroidJUnit4
import com.gregorymarkthomas.calendar.rules.MainActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is considered an INTEGRATION test suite - it utilises the Activity, therefore forcing the 'real' Model
 * TODO() - Crash in Model
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/Presenter anyway.
 * But the test below is an example that it works.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    val TAG = "MainActivityTest"

    @get:Rule
    val rule = MainActivityTestRule(null)

    @Test
    fun month_view_is_default_view() {
        onView(withTagValue(Matchers.`is`("MonthView"))).check(matches(isDisplayed()))
    }
}