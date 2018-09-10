package com.gregorymarkthomas.calendar.model

/**
 * Here is the implementation to query the AppCalendar Provider for calendar data.
 * TODO - cache AppCalendar object?
 */
class AndroidCalendarProvider: CalendarRepository {

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        callback.onGetCalendars(CalendarContentResolver().get())
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    override fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?, callback: Callback.GetEventsCallback) {
        callback.onGetEvents(EventContentResolver().get(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
    }
}