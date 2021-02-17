package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.model.interfaces.Resolver

class CalendarRepository(private val contentResolver: Resolver) {

    fun getCalendars(callback: Callback.GetCalendarsCallback) {
        callback.onGetCalendars(CalendarRetriever(contentResolver).get())
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray) {
        callback.onGetEvents(CalendarEventRetriever(contentResolver).get(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
    }
}