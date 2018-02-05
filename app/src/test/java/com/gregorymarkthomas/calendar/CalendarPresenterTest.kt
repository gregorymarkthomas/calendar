package com.gregorymarkthomas.calendar

import android.os.Bundle
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.util.LifeCycleView
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

    private lateinit var presenter: CalendarPresenter

    /** Ensure all mocks will be properly initialized before each test */
    @JvmField @Rule
    var mockitoRule = MockitoJUnit.rule()

    /** CalendarPresenter objects that it relies on require mocking **/
    @Mock
    private lateinit var mockBackstack: BackStackInterface
    @Mock
    private lateinit var mockView: CalendarViewInterface

    @Before
    fun setUp() {
        presenter = CalendarPresenter(mockView, mockBackstack)
    }

    @Test
    fun when_view_loaded_without_input_date_then_show_today_date() {
        // when
        presenter.onViewCreated(Bundle())

        // then
        then(mockView).should().setDateView(Date().toString())
    }

    @Test
    fun when_view_loaded_with_input_date_then_show_input_date() {

        val args = Bundle()
        args.putSerializable(MonthView.Companion.DATE_ARG, Date())

        // when
        presenter.onViewCreated(args)

        // then
        then(mockView).should().setDateView(args.get(MonthView.DATE_ARG).toString())
    }
}