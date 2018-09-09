package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.provider.CalendarContract.Calendars

/**
 * Here is the implementation to query the AppCalendar Provider for calendar data.
 * * TODO - cache AppCalendar object?
 */
class AndroidCalendarProvider: CalendarRepository {

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        val cursor = this.resolver.query(
                Calendars.CONTENT_URI,
                arrayOf(Calendars._ID, Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME, Calendars.OWNER_ACCOUNT),
                getDefaultWhereClause(),
                getDefaultWhereClauseArgs(),
                null
        )
        val calendars = getCalendarsFromProvider(cursor)
        cursor.close()
        callback.onGetCalendars(calendars)
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     */
    override fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?, callback: Callback.GetEventsCallback) {
        callback.onGetEvents(EventContentResolver().get(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow))
    }



    /**
     * TODO - should we calculate from the input value or can we get it from the cursor?
     */
    private fun getDayOfMonth(cursor: Cursor): Int {
        TODO("not implemented")
    }

    /**
     * TODO - should we calculate from the input value or can we get it from the cursor?
     */
    private fun getMonth(cursor: Cursor): Int {
        TODO("not implemented")
    }

    /**
     * TODO - should we calculate from the input value or can we get it from the cursor?
     */
    private fun getYear(cursor: Cursor): Int {
        TODO("not implemented")
    }

    private fun getEvents(cursor: Cursor): MutableList<AppEvent> {
        TODO("not implemented")
    }
}