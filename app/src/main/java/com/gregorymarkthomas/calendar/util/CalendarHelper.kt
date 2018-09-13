package com.gregorymarkthomas.calendar.util

import java.util.*

object CalendarHelper {
    fun getNewCalendar(): Calendar {
        val timeZone = TimeZone.getTimeZone("UTC")
        val dateHelper = Calendar.getInstance(timeZone)
        dateHelper.isLenient = true
        return dateHelper
    }
}