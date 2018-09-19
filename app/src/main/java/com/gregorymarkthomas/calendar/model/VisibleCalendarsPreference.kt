package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface

class VisibleCalendarsPreference(val preferences: SharedPreferencesInterface) {

    companion object {
        private const val VISIBLE_CALENDARS_KEY = "visible_calendars_key"
    }

    /**
     * Visible calendars are stored in Preferences as a comma-separated list of calendar ids.
     * If not yet set, return an empty IntArray. The model will recognise this and ignore Calendar Ids as a query parameter (i.e. will return events for ALL available calendars)
     * This is converted into an IntArray.
     */
    fun get(): IntArray {
        val calendarsStr = preferences.getPreferences().getString(VISIBLE_CALENDARS_KEY, null)
        return if(calendarsStr != null)
            parseCommaSeparatedStringToIntArray(calendarsStr)
        else
            IntArray(0)
    }

    fun set(visibleCalendars: IntArray) {
        preferences.getPreferences().edit().putString(VISIBLE_CALENDARS_KEY, parseIntArrayToCommaSeparatedString(visibleCalendars)).apply()
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