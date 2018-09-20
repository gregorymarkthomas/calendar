package com.gregorymarkthomas.calendar

import android.os.Bundle
import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.MonthView
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import java.util.*


class CalendarPresenterTest {

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** CalendarPresenter objects that it relies on require mocking **/
    @Mock
    private lateinit var backstack: BackStackInterface
    @Mock
    private lateinit var view: CalendarViewInterface
    @Mock
    private var args: Bundle = Bundle()
    @Mock
    private lateinit var model: Model

    @Before
    fun setUp() {
        // Do nothing
    }

    @Test
    fun when_view_loaded_with_todays_date_then_show_todays_date() {
        val now = Date()

        // when
        val presenter = CalendarPresenter(view, model, backstack, now)

        val calendar = Calendar.getInstance()
        calendar.time = now

        // then
        then(view).should().setDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    /**
     * Set date to 31st December 9999 at 23:59:59
     */
    @Test
    fun when_view_loaded_with_future_date_then_show_future_date() {
        val future = Date(253402300799000)

        // when
        val presenter = CalendarPresenter(view, model, backstack, future)

        val calendar = Calendar.getInstance()
        calendar.time = future

        // then
        then(view).should().setDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    /**
     * Set date to 1st January 1970 at 00:00:00
     */
    @Test
    fun when_view_loaded_with_past_date_then_show_past_date() {
        val past = Date(0)

        // when
        val presenter = CalendarPresenter(view, model, backstack, past)

        val calendar = Calendar.getInstance()
        calendar.time = past

        // then
        then(view).should().setDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    /**
     * TODO(): BackStackItem(MonthView::class.java)) here is a different instance to the real one, so test fails?
     * TODO(): need to change MonthView to DayView
     */
    @Test
    fun when_today_button_pressed_then_show_today_date_view() {
        val past = Date(0)

        val presenter = CalendarPresenter(view, model, backstack, past)

        // when
        presenter.onTodayButtonPress()

        // then
        then(backstack).should().goTo(MonthView::class.java, Date())
    }

    /**
     * TODO(): need to change MonthView to DayView
     * This will fail
     */
    @Test
    fun when_future_day_pressed_then_show_future_day_view() {
        val now = Date()
        val presenter = CalendarPresenter(view, model, backstack, now)

        // when
        presenter.onDayPress(AppDay(31, 12, 9999), 23)

        // then
        val future = Date(253402300799000)
        then(backstack).should().goTo(MonthView::class.java, future)
    }
}