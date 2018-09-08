package com.gregorymarkthomas.calendar.model

import java.util.*

interface ModelInterface {
    fun getTodayDate(): Date
    fun getMonthString(month: Int): String
    fun getEvents(dayInMonth: Int, month: Int, year: Int, numberOfDays: Int, calendarsToShow: IntArray? = null, callback: Callback.GetEventsCallback)
    fun getCalendars(callback: Callback.GetCalendarsCallback)
    fun getDaysInMonth(month: Int, year: Int): Int
}