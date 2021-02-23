package com.gregorymarkthomas.calendar.model.repository

import com.gregorymarkthomas.calendar.model.AppCalendar

class CalendarVisibilityRepository(private val backend: BackendRepository) {

    private val key = "visible_calendars_key"

    /**
     * Visible calendars are stored in SharedPreferences as a comma-separated list of calendar ids.
     * If not yet set, return an empty IntArray. The model will recognise this and ignore Calendar Ids as a query parameter (i.e. will return events for ALL available calendars)
     * This is converted into an IntArray.
     */
    fun getVisibleCalendars(): IntArray {
        val commaSeparatedString = backend.getData(key, "")
        return if(commaSeparatedString.isNullOrBlank())
            IntArray(0)
        else
            parseCommaSeparatedStringToIntArray(commaSeparatedString)
    }

    fun setVisibleCalendars(visibleCalendars: IntArray) {
        backend.setData(key, parseIntArrayToCommaSeparatedString(visibleCalendars))
    }

    private fun extractCalendarIds(availableCalendars: List<AppCalendar>): String {
        val calendarIds = IntArray(availableCalendars.size)
        availableCalendars.forEachIndexed { index, calendar ->
            calendarIds[index] = calendar.id
        }
        return parseIntArrayToCommaSeparatedString(calendarIds)
    }

    private fun parseCommaSeparatedStringToIntArray(commaSeparatedString: String): IntArray {
        val commaSeparatedStringArray = commaSeparatedString.split(",")
        val intArray = IntArray(commaSeparatedStringArray.size)
        commaSeparatedStringArray.forEachIndexed { index, element ->
            intArray[index] = element.toInt()
        }
        return intArray
    }

    private fun parseIntArrayToCommaSeparatedString(intArray: IntArray): String {
        var commaSeparatedString = ""
        for(value in intArray) {
            var str = value.toString()
            str += ","
            commaSeparatedString += str
        }
        return commaSeparatedString
    }
}