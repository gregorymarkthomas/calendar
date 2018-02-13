package com.gregorymarkthomas.calendar

import android.os.Bundle
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.view.MonthView
import kotlinx.android.synthetic.main.month_view.view.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.robolectric.annotation.Config
import java.util.*

@RunWith(RobolectricGradleTestRunner::class)
@Config(constants = BuildConfig::class)
public class MonthViewTest {

    lateinit var view: MonthView

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** Objects that class we're testing relies on require mocking **/
    @Mock
    private lateinit var presenter: CalendarPresenter

    @Before
    fun setUp() {
        view = MonthView(MainActivity(), Bundle())
    }

    @Test
    fun presenter_attached_to_view() {
        assertThat(view.dateTextView.text).isEqualTo("")
    }
}