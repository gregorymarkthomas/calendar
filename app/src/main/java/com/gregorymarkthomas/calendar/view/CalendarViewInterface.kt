package com.gregorymarkthomas.calendar.view

import com.gregorymarkthomas.calendar.model.AppDay
import com.gregorymarkthomas.calendar.util.interfaces.AndroidContextInterface

interface CalendarViewInterface {
    fun setSelectedDateView(dayOfMonth: Int, monthOfYear: String, year: Int)
    fun showDates(days: List<AppDay>)
}