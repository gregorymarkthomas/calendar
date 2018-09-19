package com.gregorymarkthomas.calendar.util

import java.text.DateFormatSymbols
import java.util.*

object CalendarHelper {
    fun getNewCalendar(): Calendar {
        val timeZone = TimeZone.getTimeZone("UTC")
        val dateHelper = Calendar.getInstance(timeZone)
        dateHelper.isLenient = true
        return dateHelper
    }

    /**
     * Month number starts at ZERO:
     * - January = 0
     * - February = 1
     * - March = 2
     * ...
     */
    fun getMonthString(month: Int) = DateFormatSymbols().months[month]
}