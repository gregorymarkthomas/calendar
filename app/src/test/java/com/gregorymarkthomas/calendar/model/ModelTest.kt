package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class ModelTest {

    /**
     * 'relaxUnitFun = true' will stop 'io.mockk.MockKException: no answer found for...' error; "allows creation with no specific behaviour for Unit function".
     *  We use callbacks in Model, and they all return Unit...
     */
    private val calendarRepo = mockk<CalendarRepository>(relaxUnitFun=true)
    private val accountRepo: AccountRepository = mockk()
    private val calendarVisibilityRepo: CalendarVisibilityRepository = mockk()

    @BeforeEach
    fun init() {
        clearMocks(calendarRepo, accountRepo, calendarVisibilityRepo)
    }

    @Test
    fun `get events from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetEventsCallback>()
        val properties = mockk<GetEventProperties>()

        // when
        model.getEvents(properties, callback)

        // then
        verify { calendarRepo.getEvents(properties, callback) }
    }

    @Test
    fun `get calendars from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetCalendarsCallback>()

        // when
        val accountName = "mock"
        model.getCalendars(accountName, callback)

        // then
        verify { calendarRepo.getCalendars(accountName, callback) }
    }

}