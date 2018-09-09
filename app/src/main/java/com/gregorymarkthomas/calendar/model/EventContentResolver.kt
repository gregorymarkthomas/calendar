package com.gregorymarkthomas.calendar.model

import android.database.Cursor
import android.net.Uri
import android.provider.CalendarContract.Events

class EventContentResolver: ContentResolverManager() {

    /************* public *****/
    fun get(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?): MutableList<AppDay> {
        val clause = getWhereClause(fromDayInMonth, month, year, specifiedNoOfDays, calendarsToShow)
        return get(clause)
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
    private fun getWhereClause(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int, calendarsToShow: IntArray?): String {

        var where = ""
        where = addCalendarsToQuery(calendarsToShow, where)
        where = addDaySpanToQuery(fromDayInMonth, specifiedNoOfDays, where)
        where = addMonthToQuery(month, where)
        where = addYearToQuery(year, where)

        return where
    }
}