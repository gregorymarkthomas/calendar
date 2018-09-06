package com.gregorymarkthomas.calendar.view

import com.gregorymarkthomas.calendar.util.Day

interface CalendarViewInterface {
    fun setDateView(dayOfMonth: Int, monthOfYear: String, year: Int)
    fun showDates(days: MutableList<Day>)
}