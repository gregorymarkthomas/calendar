package com.gregorymarkthomas.calendar.util

import android.preference.PreferenceManager
import com.gregorymarkthomas.calendar.CalendarApplication

object VisibleCalendarsPreference {

    private const val VISIBLE_CALENDARS_KEY = "visible_calendars_key"
    private val preferences = PreferenceManager.getDefaultSharedPreferences(CalendarApplication.applicationContext)

    /**
     * Visible calendars is stored in Preferences as a comma-separated list of calendar ids.
     * If not yet set, set as ALL available calendars.
     * This is converted into an IntArray.
     */
    fun get(availableCalendars: MutableList<AppCalendar>): IntArray {
        val calendarsStr = preferences.getString(VISIBLE_CALENDARS_KEY, extractCalendarIds(availableCalendars))
        return parseCommaSeparatedStringToIntArray(calendarsStr)
    }

    fun set(visibleCalendars: IntArray) {
        preferences.edit().putString(VISIBLE_CALENDARS_KEY, parseIntArrayToCommaSeparatedString(visibleCalendars)).apply()
    }

    private fun extractCalendarIds(availableCalendars: MutableList<AppCalendar>): String {
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