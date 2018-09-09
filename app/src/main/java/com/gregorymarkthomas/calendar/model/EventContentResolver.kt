package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Events

class EventContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?): MutableList<AppDay> {
        val (clause, args) = getWhereClause(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow)
        return get(clause, args)
    }

    /************* protected *****/
    override fun getUri(): Uri {
        return Events.CONTENT_URI
    }

    override fun getFields(): Array<String> {
        return arrayOf(Events._ID, Events.OWNER_ACCOUNT)
    }

    override fun getSortOrder(): String? {
        return null
    }

    /**
     * Converts CalendarProvider data into a list of Days
     */
    override fun getObjectFromRow(cursor: Cursor): AppDay {
        return AppDay(getDayOfMonth(cursor), getMonth(cursor), getYear(cursor), getEvents(cursor))
    }


    /************* private *****/
    /**
     * The default WHERE clause is required so that the calendars for the currently selected user is retrieved.
     * For particular queries we want to add on to the WHERE clause.
     * TODO - add fromDayInMonth, month, year and specifiedNoOfDays to the 'selection' query
     **/
    private fun getWhereClause(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?): Pair<String, Array<String>> {

        var (where, args) = Pair<String, Array<String>>("", arrayOf())
        addCalendarsToQuery(calendarsToShow, (where, args))
        addDaySpanToQuery(fromDayInMonth, specifiedNoOfDays, (where, args))
        addMonthToQuery(month, (where, args))
        addYearToQuery(year, (where, args))

        return Pair(where, args)
    }
}