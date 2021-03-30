package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import com.gregorymarkthomas.calendar.util.CalendarHelper
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import java.util.*

class ModelTest {

    private val calendarRepo: CalendarRepository = mockk()
    private val accountRepo: AccountRepository = mockk()
    private val calendarVisibilityRepo: CalendarVisibilityRepository = mockk()


    @BeforeEach
    fun init() {
        clearMocks(calendarRepo, accountRepo, calendarVisibilityRepo)
    }

    @Test
    fun `get events from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)

        // when
        model.getEvents()

        // then
        verify { calendarRepo.getEvents() }
    }
}