package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.*
import com.gregorymarkthomas.calendar.model.interfaces.Resolver

class AndroidCalendarRepository(private val contentResolver: Resolver): CalendarRepository {

    override fun getCalendars(accountName: String, callback: ModelCallback.GetCalendarsCallback) {
        callback.onGetCalendars(CalendarRetriever(contentResolver).get(accountName))
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    override fun getEvents(properties: GetEventProperties, callback: ModelCallback.GetEventsCallback) {
        callback.onGetEvents(CalendarEventRetriever(contentResolver).get(properties))
    }

    override fun getAccounts(callback: ModelCallback.GetAccountsCallback) {
        callback.onGetAccounts(AccountRetriever(contentResolver).getAvailable())
    }

    override fun getAccount(accountName: String, callback: ModelCallback.GetAccountCallback) {
        callback.onGetAccount(AccountRetriever(contentResolver).get(accountName))
    }
}