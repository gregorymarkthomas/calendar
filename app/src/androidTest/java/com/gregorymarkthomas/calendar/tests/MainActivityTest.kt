package com.gregorymarkthomas.calendar.tests

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers
import com.gregorymarkthomas.calendar.R
import com.gregorymarkthomas.calendar.rules.MainActivityTestRule
import kotlinx.android.synthetic.main.month_view.view.*

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    val TAG = "MainActivityTest"

    @get:Rule
    val rule = MainActivityTestRule()

    @Test
    fun month_view_is_default_view() {
        onView(withTagValue(Matchers.`is`("MonthView"))).check(matches(isDisplayed()))
    }
}