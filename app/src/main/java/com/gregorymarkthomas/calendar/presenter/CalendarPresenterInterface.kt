package com.gregorymarkthomas.calendar.presenter

interface CalendarPresenterInterface {
    fun onViewCreated()
    fun onDayPress(dayOfMonth: Int, monthOfYear: Int, year: Int)
    fun onTodayButtonPress()
    fun onEventPress(hours: Int, minutes: Int)
}