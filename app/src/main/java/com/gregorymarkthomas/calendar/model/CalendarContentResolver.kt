package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.util.CursorExtractor
import java.util.*

class CalendarContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(): List<AppCalendar> {
        val cursor: Cursor = get(null)
        val calendars = createCalendars(cursor)
        cursor.close()
        return calendars
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Calendars.CONTENT_URI
    }

    /**
     * TODO - Add more fields!!!
     */
    override fun getFields(): Array<String> {
        return arrayOf(Calendars._ID,
                Calendars.CALENDAR_,
                Calendars.CALENDAR_DISPLAY_NAME,
                Calendars.ACCOUNT_NAME,
                Calendars.OWNER_ACCOUNT)
    }

    /**
     * The sort order does not matter, hence null.
     */
    override fun getSortOrder(): String? {
        return null
    }


    /************* private *****/
    /**
     * Converts CalendarProvider data into a list of Calendars.
     */
    private fun createCalendars(cursor: Cursor): List<AppCalendar> {
        val calendars = mutableListOf<AppCalendar>()
        while(cursor.moveToNext()) {
            calendars.add(AppCalendar(
                    CursorExtractor.Calendar.getId(cursor),
                    CursorExtractor.Calendar.getDisplayName(cursor),
                    CursorExtractor.Calendar.getColour(cursor),
                    CursorExtractor.Calendar.getAccessLevel(cursor),
                    CursorExtractor.Calendar.getTimezone(cursor),
                    CursorExtractor.Calendar.getAccountName(cursor),
                    CursorExtractor.Calendar.getOwnerAccount(cursor)
            ))
        }
        return calendars
    }
}