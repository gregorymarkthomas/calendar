package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.mock.MockCalendarRepository
import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.BeforeEach

class ModelTest {

    private val calendarRepo = MockCalendarRepository()
    private val accountRepo: AccountRepository = mockk()
    private val calendarVisibilityRepo: CalendarVisibilityRepository = mockk()
    private val getEventsProperties: GetEventProperties = mockk()
    private val getEventsCallback: ModelCallback.GetEventsCallback = mockk()


    @BeforeEach
    fun init() {
        clearMocks(accountRepo, calendarVisibilityRepo, getEventsProperties, getEventsCallback)
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