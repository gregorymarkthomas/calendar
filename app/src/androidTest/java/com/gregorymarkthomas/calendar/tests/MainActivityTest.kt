package com.gregorymarkthomas.calendar.tests

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withTagValue
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gregorymarkthomas.calendar.rules.MainActivityTestRule
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is considered an INTEGRATION test suite - it utilises the Activity, therefore forcing the 'real' Model
 * TODO() - Crash in Model
 * We want to shy away from Instrumented tests, if possible.
 * This is because we can effectively test each View/PresenterInterface anyway.
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