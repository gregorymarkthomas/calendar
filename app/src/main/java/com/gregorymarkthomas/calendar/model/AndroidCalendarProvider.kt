package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.ContentResolverInterface

/**
 * Here is the implementation to query the AppCalendar Provider for calendar data.
 * TODO - cache AppCalendar object?
 *
 * TODO() FRIDAY 14th Sept - read this to complete this. We want to pass in a contexted class that can receive a ContentResolver. This should originate from MainActivity and be required by Model (because Model might need some Androidy things).
 * https://stackoverflow.com/questions/39100105/need-context-in-model-in-mvp
 */
class AndroidCalendarProvider(private val contentResolver: ContentResolverInterface): CalendarRepository {

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        callback.onGetCalendars(CalendarResolver(contentResolver).get())
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    override fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray, callback: Callback.GetEventsCallback) {
        callback.onGetEvents(EventResolver(contentResolver).get(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
    }
}