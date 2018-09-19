package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.interfaces.SharedPreferencesInterface
import java.util.*

interface ModelInterface {
    fun getTodayDate(): Date
    fun getMonthString(month: Int): String
    fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, callback: Callback.GetEventsCallback, calendarsToShow: IntArray = IntArray(0))
    fun getCalendars(callback: Callback.GetCalendarsCallback)
    fun getDaysInMonth(month: Int, year: Int): Int
    fun getVisibleCalendars(preferences: SharedPreferencesInterface): IntArray
    fun setVisibleCalendars(preferences: SharedPreferencesInterface, calendarsToShow: IntArray)
}