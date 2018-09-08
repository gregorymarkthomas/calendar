package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.provider.CalendarContract
import com.gregorymarkthomas.calendar.CalendarApplication
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.util.AppDay
import com.gregorymarkthomas.calendar.util.AppEvent

/**
 * Here is the implementation to query the AppCalendar Provider for calendar data.
 */
class AndroidCalendarProvider: CalendarRepository {

    companion object {
        // Projection array. Creating indices for this array instead of doing
        // dynamic lookups improves performance.
        val EVENT_PROJECTION = arrayOf(Calendars._ID, // 0
                Calendars.ACCOUNT_NAME, // 1
                Calendars.CALENDAR_DISPLAY_NAME, // 2
                Calendars.OWNER_ACCOUNT                  // 3
        )

        // The indices for the projection array above.
        private val PROJECTION_ID_INDEX = 0
        private val PROJECTION_ACCOUNT_NAME_INDEX = 1
        private val PROJECTION_DISPLAY_NAME_INDEX = 2
        private val PROJECTION_OWNER_ACCOUNT_INDEX = 3
    }

    override fun getCalendars(callback: Callback.GetCalendarsCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * This function retrieves the Days that (could) contain Events. The inputs are used to retrieve a span of days in a month and year.
     * This needs to take the CalendarProvider calendar data (via the cursor) and convert it into a list of the app's 'AppDay' objects
     * TODO - cache AppCalendar object?
     */
    override fun getEvents(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?, callback: Callback.GetEventsCallback) {
        val (selection, args) = getQuerySelection(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow)
        val cursor = query(selection, args)
        val days = getDaysFromProvider(cursor)
        cursor.close()
        callback.onGetEvents(days)
    }

    /** TODO - add fromDayInMonth, month, year and specifiedNoOfDays to the 'selection' query
      * TODO - remove hardcoded account
     **/
    private fun getQuerySelection(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?): Pair<String, Array<String>> {
        val selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" + Calendars.ACCOUNT_TYPE + " = ?) AND (" + Calendars.OWNER_ACCOUNT + " = ?))"
        val selectionArgs = arrayOf("hello@hello.com", "com.google", "hello@hello.com")

        return Pair(selection, selectionArgs)
    }

    /**
     * Converts CalendarProvider data into a list of Days
     */
    private fun getDaysFromProvider(cursor: Cursor): MutableList<AppDay> {
        val list = mutableListOf<AppDay>()
        while(cursor.moveToNext()) {
            val day = AppDay(getDayOfMonth(cursor), getMonth(cursor), getYear(cursor), getEvents(cursor))
            list.add(day)
        }

        return list;
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

    private fun query(selection: String, args: Array<String>): Cursor {
        return CalendarApplication.applicationContext.contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, args, null)
    }
}