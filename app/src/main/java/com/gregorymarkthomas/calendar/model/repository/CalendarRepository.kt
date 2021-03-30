package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.*

interface CalendarRepository {
    fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback)

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    fun getEvents(properties: GetEventProperties, callback: ModelCallback.GetEventsCallback)
    fun getAccounts(callback: ModelCallback.GetAccountsCallback)
    fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback)
}