package com.gregorymarkthomas.calendar

import android.os.Bundle
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.util.AppDay
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.util.backstack.BackStackItem
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

    /**
     * CalendarPresenter has a null date by default
     */
    @Test
    fun when_view_loaded_without_input_date_then_show_today_date() {
        // when
        var presenter = CalendarPresenter(view, backstack)

        val calendar = Calendar.getInstance()
        calendar.time = Date()

        // then
        then(view).should().setDateView(calendar.get(Calendar.DAY_OF_MONTH), model.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    /**
     * Set date to 'now'
     */
    @Test
    fun when_view_loaded_with_input_date_then_show_input_date() {
        val now = Date()

        // when
        var presenter = CalendarPresenter(view, backstack, now)

        val calendar = Calendar.getInstance()
        calendar.time = now

        // then
        then(view).should().setDateView(calendar.get(Calendar.DAY_OF_MONTH), model.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR))
    }

    /**
     * TODO(): BackStackItem(MonthView::class.java)) here is a different instance to the real one, so test fails?
     * TODO(): need to change MonthView to DayView
     */
    @Test
    fun when_today_button_pressed_then_show_today_date_view() {

        var presenter = CalendarPresenter(view, backstack)

        // when
        presenter.onTodayButtonPress()

        // then
        then(backstack).should().goTo(BackStackItem(MonthView::class.java))
    }

    /**
     * TODO(): need to change MonthView to DayView
     * This will fail
     */
    @Test
    fun when_future_day_pressed_then_show_future_day_view() {
        var presenter = CalendarPresenter(view, backstack)

        // when
        presenter.onDayPress(AppDay(19, 7, 2050), 12)

        // then
        then(backstack).should().goTo(BackStackItem(MonthView::class.java))
    }
}