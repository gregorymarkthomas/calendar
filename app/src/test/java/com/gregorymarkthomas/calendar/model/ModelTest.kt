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
    private val getEventsProperties = mockk<GetEventProperties>()
    private val getEventsCallback = mockk<ModelCallback.GetEventsCallback>()

    @BeforeEach
    fun init() {
        clearMocks(accountRepo, calendarVisibilityRepo, getEventsProperties)
    }

    @Test
    fun `get events from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)

        // when
        model.getEvents(getEventsProperties, getEventsCallback)

        // then
        verify { calendarRepo.getEvents(getEventsProperties, getEventsCallback) }
    }

}