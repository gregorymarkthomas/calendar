package com.gregorymarkthomas.calendar.util

import com.gregorymarkthomas.calendar.model.AppCalendar
import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface

object VisibleCalendarsPreference {

    private const val VISIBLE_CALENDARS_KEY = "visible_calendars_key"

    /**
     * Visible calendars is stored in Preferences as a comma-separated list of calendar ids.
     * If not yet set, set as ALL available calendars.
     * This is converted into an IntArray.
     */
    fun get(preferences: SharedPreferencesInterface, availableCalendars: List<AppCalendar>): IntArray {
        val calendarsStr = preferences.getPreferences().getString(VISIBLE_CALENDARS_KEY, extractCalendarIds(availableCalendars))
        return parseCommaSeparatedStringToIntArray(calendarsStr)
    }

    fun set(preferences: SharedPreferencesInterface, visibleCalendars: IntArray) {
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