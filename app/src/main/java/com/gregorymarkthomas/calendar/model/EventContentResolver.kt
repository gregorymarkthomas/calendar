package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Events

class EventContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray): MutableList<AppDay> {
        val clause = getWhereClause(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow)
        return get(clause) as MutableList<AppDay>
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Events.CONTENT_URI
    }

    /**
     * TODO - add more necessary fields
     */
    override fun getFields(): Array<String> {
        return arrayOf(Events._ID, Events.OWNER_ACCOUNT, Events.DTSTART, Events.DTEND, Events.DURATION, Events.ALL_DAY)
    }

    override fun getSortOrder(): String? {
        return Events.DTSTART + ASCENDING
    }

    /**
     * Converts CalendarProvider EVENT data into a list of Days.
     * TODO() - this needs to return a List of DAYS - each day in the list will have some or no Events.
     * TODO() - we need to put Events with Days
     */
    override fun convertContentIntoObjects(cursor: Cursor): MutableList<Any> {
        val events = mutableListOf<Any>()
        while(cursor.moveToNext()) {
            events.add(AppEvent(getCalendar(cursor), getStartDate(cursor), getEndDate(cursor)))
        }
        return compileDays(events)
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

    /**
     * TODO - create list of Days - there should be the INPUT amount of days
     * TODO - we need to find a way to use the original INPUT noOfDays etc
     * TODO - should I rethink this?
     */
    private fun compileDays(events: MutableList<AppEvent>): MutableList<AppDay> {
        AppDay(getDayOfMonth(cursor), getMonth(cursor), getYear(cursor), getEvents(cursor))
    }

    /**
     * TODO - should we calculate from the input value or can we get it from the cursor?
     * It may well be a pain to calculate the date from
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