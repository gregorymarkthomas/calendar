package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.provider.CalendarContract
import com.gregorymarkthomas.calendar.CalendarApplication
import android.provider.CalendarContract.Calendars

/**
 * Here is the implementation to query the Calendar Provider for calendar data.
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

    /**
     * TODO - remove hardcoded account
     */
    override fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback) {
        val selection = "((" + Calendars.ACCOUNT_NAME + " = ?) AND (" + Calendars.ACCOUNT_TYPE + " = ?) AND (" + Calendars.OWNER_ACCOUNT + " = ?))"
        val selectionArgs = arrayOf("hello@hello.com", "com.google", "hello@hello.com")

        val cursor = query(selection, selectionArgs)

        // TODO - extract data into 'days'
        Mut
        cursor.close()

        callback.onGetEvents(days)
    }

    private fun query(selection: String, selectionArgs: Array<String>): Cursor {
        return CalendarApplication.applicationContext.contentResolver.query(CalendarContract.Calendars.CONTENT_URI, EVENT_PROJECTION, selection, selectionArgs, null)
    }
}