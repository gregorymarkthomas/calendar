package com.gregorymarkthomas.calendar.model

import com.gregorymarkthomas.calendar.util.CalendarHelper
import java.util.*

class AppDay(var dayOfMonth: Int, var month: Int, var year: Int, val events: List<AppEvent> = listOf()) {

    /**
     * The Date representation of this Day. This is in UTC.
     */
    var date: Date

    /**
     * The start (00:00) time in milliseconds for this day. This is in UTC.
     */
    var startEpoch: Long

    /**
     * The end (23:59) time in milliseconds for this day. This is in UTC.
     */
    var endEpoch: Long

    companion object {
        /**
         * Creates empty list of x days. These day objects do not yet have events
         */
        fun createEmptyDays(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int): List<AppDay> {
            val days = mutableListOf<AppDay>()
            for(i in fromDayInMonth..fromDayInMonth + specifiedNoOfDays) {
                days.add(AppDay(i, month, year))
            }
            return days
        }
    }

    /**
     * We want to ensure that the input data is set correctly.
     * We do this by using the Calendar class (set to 'lenient').
     * This will take '32nd day of December 2018' as '1st January 2019'.
     */
    init {
        val dateHelper = CalendarHelper.getNewCalendar()
        dateHelper.set(year, month, dayOfMonth)
        year = dateHelper.get(Calendar.YEAR)
        month = dateHelper.get(Calendar.MONTH)
        dayOfMonth = dateHelper.get(Calendar.DAY_OF_MONTH)
        date = dateHelper.time

        startEpoch = setStartEpoch(year, month, dayOfMonth)
        endEpoch = setEndEpoch(year, month, dayOfMonth)
    }

    private fun setStartEpoch(year: Int, month: Int, dayOfMonth: Int): Long {
        val dateHelper = CalendarHelper.getNewCalendar()
        dateHelper.set(year, month, dayOfMonth, 0, 0)
        return dateHelper.timeInMillis
    }

    private fun setEndEpoch(year: Int, month: Int, dayOfMonth: Int): Long {
        val dateHelper = CalendarHelper.getNewCalendar()
        dateHelper.set(year, month, dayOfMonth, 23, 59)
        return dateHelper.timeInMillis
    }

}