package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Calendars
import com.gregorymarkthomas.calendar.model.interfaces.Resolver
import com.gregorymarkthomas.calendar.util.CursorExtractor

class CalendarRetriever(private val resolver: Resolver): CalendarResolverQuery(resolver) {

    /************* public *****/
    fun get(accountName: String): List<AppCalendar> {
        val cursor = query(accountName, null)
        var calendars: List<AppCalendar> = listOf()
        if(cursor != null) {
            calendars = createCalendars(cursor)
            cursor.close()
        }
        return calendars
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Calendars.CONTENT_URI
    }

    override fun getFields(): Array<String> {
        return arrayOf(Calendars._ID,
                Calendars.CALENDAR_DISPLAY_NAME,
                Calendars.CALENDAR_COLOR,
                Calendars.CALENDAR_ACCESS_LEVEL,
                Calendars.CALENDAR_TIME_ZONE,
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