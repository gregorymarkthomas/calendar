package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.repository.AccountRepository
import com.gregorymarkthomas.calendar.model.repository.BackendRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository
import com.gregorymarkthomas.calendar.model.repository.CalendarVisibilityRepository
import io.mockk.clearMocks
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import java.util.*

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

    @Test
    fun `get available accounts from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetAccountsCallback>()

        // when
        model.getAvailableAccounts(callback)

        // then
        verify { calendarRepo.getAccounts(callback) }
    }

    @Test
    fun `get single account from calendar repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetAccountCallback>()

        // when
        val accountName = "mock"
        model.getAccount(accountName, callback)

        // then
        verify { calendarRepo.getAccount(accountName, callback) }
    }

    @Test
    fun `get saved account`() {
        val accountName = "mock"
        val accountRepo = AccountRepository(mockk<BackendRepository>())
        accountRepo.setData(accountName)
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetAccountCallback>()

        // when
        model.getSavedAccount(callback)

        // then
        verify { calendarRepo.getAccount(accountName, callback) }
    }

    @Test
    fun `get null account`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)
        val callback = mockk<ModelCallback.GetAccountCallback>()

        // when
        model.getSavedAccount(callback)

        // then
        verify { callback.onGetAccount(null) }
    }

    @Test
    fun `set account to account repository`() {
        val accountRepo: AccountRepository = mockk(relaxUnitFun = true)
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)

        // when
        val accountName = "mock"
        model.setAccount(accountName)

        // then
        verify { accountRepo.setData(accountName) }
    }

    @Test
    fun `get visible calendars from calendar visibility repository`() {
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)

        // when
        model.getVisibleCalendars()

        // then
        verify { calendarVisibilityRepo.getData() }
    }

    @Test
    fun `set visible calendars to calendar visibility repository`() {
        val calendarVisibilityRepo: CalendarVisibilityRepository = mockk(relaxUnitFun = true)
        val model = Model(calendarRepo, accountRepo, calendarVisibilityRepo)

        // when
        val visibleCalendars = intArrayOf()
        model.setVisibleCalendars(visibleCalendars)

        // then
        verify { calendarVisibilityRepo.setData(visibleCalendars) }
    }
}