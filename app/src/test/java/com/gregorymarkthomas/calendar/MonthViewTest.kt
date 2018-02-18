package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
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
import kotlin.test.assertNotEquals
import kotlin.test.assertNotNull


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
        view = activity.getCurrentView() as MonthView
    }

    @Test
    fun presenter_attached_to_view() {
        assertNotNull(view)
        assertNotNull(view.dateTextView)
        assertNotEquals("", view.dateTextView.text)
    }
}