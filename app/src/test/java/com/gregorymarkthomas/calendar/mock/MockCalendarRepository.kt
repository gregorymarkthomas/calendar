package com.gregorymarkthomas.calendar.mock

import com.gregorymarkthomas.calendar.model.GetEventProperties
import com.gregorymarkthomas.calendar.model.ModelCallback
import com.gregorymarkthomas.calendar.model.repository.CalendarRepository

class MockCalendarRepository(): CalendarRepository {
    override fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback) {
        // Do nothing
    }

    override fun getEvents(properties: GetEventProperties, callback: ModelCallback.GetEventsCallback) {
        // Do nothing
    }

    override fun getAccounts(callback: ModelCallback.GetAccountsCallback) {
        // Do nothing
    }

    override fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback) {
        // Do nothing
    }
}