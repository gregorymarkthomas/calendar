package com.gregorymarkthomas.calendar.presenter

import java.util.*

interface CalendarPresenterInterface {
    fun onViewCreated(date: Date)
    fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int)
    fun onTodayButtonPress()
    fun onEventPress(hours: Int, minutes: Int)
}