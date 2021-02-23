package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.*
import com.gregorymarkthomas.calendar.model.interfaces.Resolver

class CalendarRepository(private val contentResolver: Resolver) {

    fun getCalendars(account: AppAccount, callback: Callback.GetCalendarsCallback) {
        callback.onGetCalendars(CalendarRetriever(contentResolver).get(account))
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    fun getEvents(account: AppAccount, fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        callback.onGetEvents(CalendarEventRetriever(contentResolver).get(account, fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
    }

    fun getAccounts(callback: Callback.GetAccountsCallback) {
        callback.onGetAccounts(AccountRetriever(contentResolver).get())
    }
}