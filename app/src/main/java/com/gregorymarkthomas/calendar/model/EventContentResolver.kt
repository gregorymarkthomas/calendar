package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract
import android.provider.CalendarContract.Events
import com.gregorymarkthomas.calendar.util.CursorExtractor
import java.util.*

class EventContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray): List<AppDay> {
        val clause = getWhereClause(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow)
        val cursor: Cursor = get(clause)
        val events = getEvents(cursor)
        cursor.close()
        return getDays(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow, events)
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Events.CONTENT_URI
    }

    override fun getFields(): Array<String> {
        return arrayOf(Events._ID,
                Events.DTSTART,
                Events.DTEND,
                Events.DURATION,
                Events.ALL_DAY,
                Events.EVENT_COLOR,
                Events.EVENT_LOCATION,
                Events.EVENT_TIMEZONE,
                Events.EVENT_END_TIMEZONE,
                Events.HAS_ALARM,
                Events.DESCRIPTION,
                Events.TITLE,
                Events.ORGANIZER,
                Events.CALENDAR_ID,
                Events.CALENDAR_DISPLAY_NAME,
                Events.CALENDAR_COLOR,
                Events.CALENDAR_ACCESS_LEVEL,
                Events.CALENDAR_TIME_ZONE,
                Events.OWNER_ACCOUNT,
                Events.ACCOUNT_NAME)
    }

    override fun getSortOrder(): String? {
        return Events.DTSTART + ASCENDING
    }


    /************* private *****/
    /**
     * The default WHERE clause is required so that the calendars for the currently selected user is retrieved.
     * For particular queries we want to add on to the WHERE clause.
     **/
    private fun getWhereClause(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray): String {
        var where = ""
        where += addCalendarsToQuery(calendarsToShow)
        where += addDaySpanToQuery(fromDayInMonth, month, year, specifiedNoOfDays)
        return where
    }

    private fun addCalendarsToQuery(calendarsToShow: IntArray): String {
        var queryPart = ""
        if(calendarsToShow.isNotEmpty()) {
            queryPart = AND
            calendarsToShow.forEachIndexed { index, calendarId ->
                queryPart += "(" + Events.CALENDAR_ID + EQUALS + calendarId + ")"
                if(index < calendarsToShow.size - 1)
                    queryPart += OR
            }
        }
        return queryPart
    }

    private fun getEvents(cursor: Cursor): List<AppEvent> {
        val events = mutableListOf<AppEvent>()
        while(cursor.moveToNext()) {
            events.add(AppEvent(
                    CursorExtractor.Event.getId(cursor),
                    CursorExtractor.Event.getCalendar(cursor),
                    CursorExtractor.Event.getStartDate(cursor),
                    CursorExtractor.Event.getEndDate(cursor),
                    CursorExtractor.Event.getIsAllDay(cursor),
                    CursorExtractor.Event.getTitle(cursor),
                    CursorExtractor.Event.getDescription(cursor),
                    CursorExtractor.Event.getColour(cursor),
                    CursorExtractor.Event.getLocation(cursor),
                    CursorExtractor.Event.getTimezone(cursor),
                    CursorExtractor.Event.getEndTimezone(cursor),
                    CursorExtractor.Event.getAlarmStatus(cursor)
            ))
        }
        return events
    }

    /**
     * Converts CalendarProvider EVENT data into a list of Days.
     */
    private fun getDays(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray, events: List<AppEvent>): List<AppDay> {
        val days = AppDay.createEmptyDays(fromDayInMonth, month, year, specifiedNoOfDays)
        for(event in events) {

            /** Find the DAYS (the time periods, not AppDays) that relate to this event **/


            /** For each time period, find the AppDays **/

            /** Loop through each AppDay and (attempt to) add the event **/


            /** TODO - need to find the 'day' for this event and add it to the day in question **/
            if(!isEventAddedToDay(event, day))
                day.
        }
        return days
    }



    /**
     *  So long as an event has ANYTHING to do with this range of time, then grab it from the Provider.
     *  i.e. If the event starts on any of these days, include it.
     *  i.e. If the event ends on any of these days, include it.
     *  i.e. If the event spans over any of these days, include it.
     *
     *  We want to capture events that have started at 11pm and continued to 1am of the next day.
     *  Likewise, if an event starts on the final day and ends the next day (which we are not currently catering for), then we want to capture it.
     *  Also, if an event starts before our chosen period, and ends after it, then of course we want to capture that event too.
     */
    private fun addDaySpanToQuery(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int): String {
        val startEpoch = getEpoch(fromDayInMonth, month, year)
        val endEpoch = getEpoch(fromDayInMonth + specifiedNoOfDays, month, year)

        val str = StringBuilder("")
        if(startEpoch <= endEpoch) {
            str.append(AND)
            str.append("(")

            str.append("(")
            str.append(Events.DTSTART + GREATER_THAN_OR_EQUAL + startEpoch)
            str.append(AND)
            str.append(Events.DTSTART + LESS_THAN_OR_EQUAL + endEpoch)
            str.append(")")

            str.append(OR)

            str.append("(")
            str.append(Events.DTEND + GREATER_THAN + startEpoch)
            str.append(AND)
            str.append(Events.DTEND + LESS_THAN_OR_EQUAL + endEpoch)
            str.append(")")

            str.append(OR)

            str.append("(")
            str.append(Events.DTSTART + LESS_THAN + startEpoch)
            str.append(AND)
            str.append(Events.DTEND + GREATER_THAN + endEpoch)
            str.append(")")

            str.append(")")
        } else
            throw ArithmeticException("startEpoch ('" + startEpoch + "') is greater than endEpoch ('" + endEpoch + "')")

        return str.toString()
    }
}