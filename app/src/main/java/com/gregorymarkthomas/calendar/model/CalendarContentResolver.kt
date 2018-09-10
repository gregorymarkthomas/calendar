package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Calendars

class CalendarContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(): MutableList<AppCalendar> {
        return get(null)
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Calendars.CONTENT_URI
    }

    /**
     * TODO - Add more fields
     */
    override fun getFields(): Array<String> {
        return arrayOf(Calendars._ID, Calendars.ACCOUNT_NAME, Calendars.CALENDAR_DISPLAY_NAME, Calendars.OWNER_ACCOUNT)
    }

    /**
     * The sort order does not matter, hence null.
     */
    override fun getSortOrder(): String? {
        return null
    }

    /**
     * Converts CalendarProvider data into a list of Calendars.
     * TODO - need to define AppCalendar
     */
    override fun getObjectFromRow(cursor: Cursor): AppCalendar {
        return AppCalendar()
    }


    /************* private *****/
}