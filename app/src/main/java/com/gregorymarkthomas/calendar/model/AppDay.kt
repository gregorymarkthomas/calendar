package com.gregorymarkthomas.calendar.model

import java.util.*

class AppDay(var dayOfMonth: Int, var month: Int, var year: Int, val events: MutableList<AppEvent> = mutableListOf()) {

    companion object {
        /**
         * Creates empty list of x days. These day objects do not yet have events
         */
        fun createEmptyDays(fromDayInMonth: Int, month: Int, year: Int, specifiedNoOfDays: Int): List<AppDay> {
            val days = mutableListOf<AppDay>()
            for(i in fromDayInMonth..specifiedNoOfDays) {
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
        val dateHelper = Calendar.getInstance()
        dateHelper.isLenient = true
        dateHelper.set(year, month, dayOfMonth)
        year = dateHelper.get(Calendar.YEAR)
        month = dateHelper.get(Calendar.MONTH)
        dayOfMonth = dateHelper.get(Calendar.DAY_OF_MONTH)
    }
}