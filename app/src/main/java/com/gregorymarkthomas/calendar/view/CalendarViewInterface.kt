package com.gregorymarkthomas.calendar.view

import com.gregorymarkthomas.calendar.model.AppDay

interface CalendarViewInterface {
    fun setDateView(dayOfMonth: Int, monthOfYear: String, year: Int)
    fun showDates(days: List<AppDay>)
}