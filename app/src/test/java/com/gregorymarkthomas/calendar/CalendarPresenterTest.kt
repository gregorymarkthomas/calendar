package com.gregorymarkthomas.calendar

import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.model.Model
import com.gregorymarkthomas.calendar.presenter.CalendarPresenter
import com.gregorymarkthomas.calendar.util.CalendarHelper
import com.gregorymarkthomas.calendar.util.backstack.BackStackInterface
import com.gregorymarkthomas.calendar.view.CalendarViewInterface
import com.gregorymarkthomas.calendar.view.DayView
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import java.util.*


class CalendarPresenterTest {

    /**
     * 'relaxUnitFun = true' will stop 'io.mockk.MockKException: no answer found for...' error for our simple use of a mocked View
     */
    private val view: CalendarViewInterface = mockk(relaxUnitFun=true)

    /**
     * 'relaxUnitFun = true' will stop 'io.mockk.MockKException: no answer found for...' error for our simple use of a mocked Model
     */
    private val model: Model = mockk(relaxUnitFun=true)
    private val backstack: BackStackInterface = mockk()

    @BeforeEach
    fun init() {
        clearMocks(backstack, view, model)
    }

    @Test
    fun `shows today's date`() {
        val now = Date()

        // when
        val presenter = CalendarPresenter(view, model, backstack, now)

        val calendar = Calendar.getInstance()
        calendar.time = now

        // then
        verify { view.setSelectedDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR)) }
    }

    /**
     * Set date to 31st December 9999 at 23:59:59
     */
    @Test
    fun `shows future date`() {
        val future = Date(253402300799000)

        // when
        val presenter = CalendarPresenter(view, model, backstack, future)

        val calendar = Calendar.getInstance()
        calendar.time = future

        // then
        verify { view.setSelectedDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR)) }
    }

    /**
     * Set date to 1st January 1970 at 00:00:00
     */
    @Test
    fun `shows past date`() {
        val past = Date(0)

        // when
        val presenter = CalendarPresenter(view, model, backstack, past)

        val calendar = Calendar.getInstance()
        calendar.time = past

        // then
        verify { view.setSelectedDateView(calendar.get(Calendar.DAY_OF_MONTH), CalendarHelper.getMonthString(calendar.get(Calendar.MONTH)), calendar.get(Calendar.YEAR)) }
    }

    /**
     * TODO(): verify is vague (i.e. does not include the specific date). This is because we would need to instantiate a new BackStackView in the call to goTo().
     */
    @Test
    fun `today button shows day view of today`() {
        val past = Date(0)

        val presenter = CalendarPresenter(view, model, backstack, past)

        // when
        presenter.onTodayButtonPress()

        // then
        verify { backstack.goTo(Any() as DayView) }
    }

    /**
     * TODO(): verify is vague (i.e. does not include the specific date). This is because we would need to instantiate a new BackStackView in the call to goTo().
     * This will fail
     */
    @Test
    fun `day selection shows day view of chosen date`() {
        val now = Date()
        val presenter = CalendarPresenter(view, model, backstack, now)

        // when
        presenter.onDayPress(AppDay(31, 12, 9999), 23)

        // then
        val future = Date(253402300799000)
        verify { backstack.goTo(Any() as DayView) }
    }
}